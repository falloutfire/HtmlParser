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
package com.github.falloutfire.htmlparser.Entity;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.net.URI;

public class Page {

    private URI uri;
    private String domain;
    private String title;
    private String image;
    private Document document;
    private Elements article;

    public Page(URI uri, String domain) {
        this.uri = uri;
        this.domain = domain;
    }

    public Page(URI uri, String domain, Document document) {
        this.uri = uri;
        this.domain = domain;
        this.document = document;
    }

    public Page(URI uri, String domain, Document document, Elements article) {
        this.uri = uri;
        this.domain = domain;
        this.document = document;
        this.article = article;
    }

    public URI getUri() {
        return uri;
    }

    public void setUri(URI uri) {
        this.uri = uri;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public Elements getArticle() {
        return article;
    }

    public void setArticle(Elements article) {
        this.article = article;
    }
}

