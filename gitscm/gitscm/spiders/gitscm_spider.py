#!/usr/bin/env python

from scrapy.spider import BaseSpider
from scrapy.selector import HtmlXPathSelector
from scrapy.http import Request
from gitscm.items import GitScmItem

class GitScmSpider(BaseSpider):
    name = 'gitscm'
    allowed_domains = ['www.webarch.org']
    start_urls = ['http://www.webarch.org/ProGit/']

    def parse(self, response):
        sel = HtmlXPathSelector(response)
        sites = sel.select('//div[@id=\'content\']')
        if len(sites) == 1:
            print 'url is ', response.url
            filename = response.url.split('/')[-1]
            if filename:
                open(filename, 'w').write(response.body)

        sites = sel.select('//div//ul//li//h1 | //div//ul//ul//li')
        items = []
        for site in sites:
            item = GitScmItem()
            chap = site.select('text()').extract()
            chap_name = site.select('a/text()').extract()
            item['title'] = chap + chap_name
            item['link'] = site.select('a/@href').extract()
            link = ''.join(item['link'])
            item['filename'] = item['link']
            if link:
                items.append(item)
                yield item
                following_url = self.start_urls[0] + link
                yield Request(following_url, callback = self.parse)
