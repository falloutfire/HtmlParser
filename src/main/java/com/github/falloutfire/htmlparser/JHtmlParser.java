/**
 * Copyright © 2019 falloutfire (ilya.lichachev@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.falloutfire.htmlparser;

import com.github.falloutfire.htmlparser.Entity.Page;
import com.github.falloutfire.htmlparser.ParserStrategy.Impl.ArticleBlockStrategy;
import com.github.falloutfire.htmlparser.ParserStrategy.Impl.DivContentStrategy;
import com.github.falloutfire.htmlparser.ParserStrategy.ParserStrategy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class JHtmlParser {

    private Page page;
    private ParserStrategy parserStrategy;
    private String userAgent;

    public JHtmlParser(URI uri, String userAgent) {
        this.userAgent = userAgent;
        page = new Page(uri, setDomainName(uri));
    }

    public JHtmlParser(String url, String userAgent) throws URISyntaxException {
        URI uri = new URI(url);
        this.userAgent = userAgent;
        page = new Page(uri, setDomainName(uri));
    }

    private String setDomainName(URI uri) {
        return uri.toString().startsWith("www.")
                ? "https://".concat(uri.toString().substring(4))
                : uri.toString();
    }

    public final void init() throws IOException {
        parse();
    }

    private void parse() throws IOException {
        Document document = Jsoup.connect(page.getUri().toString())
                .userAgent(userAgent)
                //.userAgent("Chrome/4.0.249.0 Safari/532.5")
                .referrer("http://www.google.com")
                .get();

        page.setDocument(document);

        Elements article = document.select("article");
        if (!article.isEmpty()) {
            page.setArticle(article);
            System.out.println("article " + page.getDomain());
            parserStrategy = new ArticleBlockStrategy();
            parserStrategy.parse(page);
            return;
        }

        article = document.select("div[class*=article]");
        if (!article.isEmpty()) {
            page.setArticle(article);
            System.out.println("div article " + page.getDomain());
            parserStrategy = new DivContentStrategy();
            parserStrategy.parse(page);
            return;
        }

        article = document.select("div[id~=(?i)([Bb](ody|log)(|[ _-])[Cc]ontent)(?i)]");
        if (!article.isEmpty()) {
            page.setArticle(article);
            System.out.println("id " + page.getDomain());
            parserStrategy = new DivContentStrategy();
            parserStrategy.parse(page);
            return;
        }

        article = document.select("div[class~=(?i)([Bb](ody|log)(|[ _-])[Cc]ontent)(?i)]");
        if (!article.isEmpty()) {
            page.setArticle(article);
            System.out.println("class " + page.getDomain());
            parserStrategy = new DivContentStrategy();
            parserStrategy.parse(page);
            return;
        }

        article = document.select("div[class~=(?i)([Dd]etail(|[ _-])[Cc]ontent)(?i)]");
        if (!article.isEmpty()) {
            page.setArticle(article);
            System.out.println("detail " + page.getDomain());
            parserStrategy = new DivContentStrategy();
            parserStrategy.parse(page);
            return;
        }
    }

    public final String outerHtml() {
        return page.getArticle().outerHtml();
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }
}

