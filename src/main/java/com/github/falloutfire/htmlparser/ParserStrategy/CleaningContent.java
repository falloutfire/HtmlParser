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
package com.github.falloutfire.htmlparser.ParserStrategy;

import com.github.falloutfire.htmlparser.Entity.Page;
import org.jsoup.nodes.Node;

public class CleaningContent {

    public static void clean(Page page) {
        repairImages(page);
        repairSrc(page);
        removeTrashBlock(page);
        removeScripts(page);
    }

    private static void removeTrashBlock(Page page) {
        page.getArticle().select("div[class~=(?i)meta(?i)]").forEach(Node::remove);
        page.getArticle().select("div[class~=(?i)share(?i)]").forEach(Node::remove);
        page.getArticle().select("div[class~=(?i)comment(?i)]").forEach(Node::remove);
        page.getArticle().select("div[class~=(?i)widgets(?i)]").forEach(Node::remove);
        page.getArticle().select("div[class~=(?i)favorite(?i)]").forEach(Node::remove);
        page.getArticle().select("div[class~=(?i)audio(?i)]").forEach(Node::remove);
        page.getArticle().select("div[class~=(?i)(T|t)oolbar(?i)]").forEach(Node::remove);

        /**
         * Все элементы div, у которых нет дочерних элементов уровня блока, в
         * р
         */
        //"<(a|blockquote|dl|div|img|ol|p|pre|table|ul)"
        page.getArticle().select("div, p, pre, ul").forEach(element -> {
            if (element.children().isEmpty()) {
                element.tagName("p");
                element.removeAttr("style");
                element.removeAttr("width");
                element.removeAttr("height");
            }
        });
    }

    private static void repairSrc(Page page) {
        page.getArticle().select("[src],[href]").forEach(element -> {
            if (!element.attr("href").startsWith("http")) {
                element.attr("href", element.attr("href").startsWith("//")
                        ? "https:" + element.attr("href")
                        : page.getDomain() + element.attr("href"));
            }

            if (!element.attr("src").startsWith("http")) {
                element.attr("src", element.attr("src").startsWith("//")
                        ? "https:" + element.attr("src")
                        : page.getDomain() + element.attr("src"));
            }
        });
    }

    private static void removeScripts(Page page) {
        page.getArticle().select("script, noscript").forEach(Node::remove);
    }

    private static void repairImages(Page page) {
        page.getArticle().select("div[class~=(((?i))image(?i))]").forEach(element -> {
            if (element.hasAttr("data-image-src")) {
                element.tagName("img");
                element.attr("src", element.attr("data-image-src"));
                element.attr("style", "width: 100%;height: auto; display: block;");
            }
        });
    }
}

