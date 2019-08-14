HtmlParser
============

HtmlParser is a Java library that parses HTML as input and returns clean, easy-to-read text.

EXAMPLES
-------------------------

Instantiate the JHtmlParser class via any one of the provided constructors, depending on where the interested HTML page is from:

```
JHtmlParser jHtmlParser = new JHtmlParser(uri, userAgent); // URI
JHtmlParser jHtmlParser = new JHtmlParser(url, userAgent);  // URL
```

Start content extraction by running:
```
jHtmlParser.init();
```

The output is clean, readable content in HTML format. You can obtain the output with:
```
jHtmlParser.outerHtml();
```

Also you can obtain the output object Page, which contain domain site, source document and output article:

```
jHtmlParser.getPage();
```

INCLUDING IN YOUR PROJECT
-------------------------
If you use Maven to build your project you can simply add a dependency to this library.

        <dependency>
            <groupId>com.github.falloutfire</groupId>
            <artifactId>htmlparser</artifactId>
            <version>0.2.2</version>
        </dependency>

If you use Gradle to build your project you can simply add a dependency to this library.

        implementation 'com.github.falloutfire:htmlparser:0.2.2'
        
DEPENDENCY
----------
        
HtmlParser depends on the [jsoup](https://github.com/jhy/jsoup/) library.
