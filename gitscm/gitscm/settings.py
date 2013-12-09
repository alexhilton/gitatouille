# Scrapy settings for gitscm project
#
# For simplicity, this file contains only the most important settings by
# default. All the other settings are documented here:
#
#     http://doc.scrapy.org/en/latest/topics/settings.html
#

BOT_NAME = 'gitscm'

SPIDER_MODULES = ['gitscm.spiders']
NEWSPIDER_MODULE = 'gitscm.spiders'

#DOWNLOADER_MIDDLEWARES = {
        #'scrapy.contrib.downloadermiddleware.httpproxy.HttpProxyMiddleware': 110,
        #'gitscm.proxy.ProxyMiddleware': 100,
#}
# Crawl responsibly by identifying yourself (and your website) on the user-agent
#USER_AGENT = 'gitscm (+http://www.yourdomain.com)'
