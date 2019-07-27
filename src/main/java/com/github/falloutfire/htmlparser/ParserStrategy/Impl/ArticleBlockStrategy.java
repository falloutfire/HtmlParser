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
package com.github.falloutfire.htmlparser.ParserStrategy.Impl;

import com.github.falloutfire.htmlparser.Entity.Page;
import com.github.falloutfire.htmlparser.ParserStrategy.CleaningContent;
import com.github.falloutfire.htmlparser.ParserStrategy.ParserStrategy;

public class ArticleBlockStrategy implements ParserStrategy {

    @Override
    public Page parse(Page page) {
        CleaningContent.clean(page);
        return page;
    }
}
