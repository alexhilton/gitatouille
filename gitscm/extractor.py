#!/usr/bin/env python

from urllib import urlretrieve
import os
import os.path
import re

IMAGE_BASE_URL = 'http://www.webarch.org/ProGit/images/'
def changeHeader(content):
    return re.sub('<div id=\'header\'>.*?</div>',
              '''<div data-role="header" data-position="fixed">
<div data-role="controlgroup" data-type="horizontal">
     <a href="#" data-ajax="false" data-role="button" data-icon="arrow-l" data-inline="true">Back</a>
     <a href="index.html" data-ajax="false" data-role="button" data-icon="home" data-inline="true">Home</a>
     <a href="#" data-ajax="false" data-role="button" data-icon="search" data-inline="true">Search</a>
</div>
</div>''',
              content, 0, re.MULTILINE | re.DOTALL)

def changeFooter(content):
    return re.sub('</div>\s+<div id=\'footer\'>.*?</div>',
                  '''<div data-role="footer" data-position="fixed">
    <div data-role="controlgroup" data-type="horizontal">
        <a href="#" data-ajax="false" data-role="button" data-icon="arrow-l" data-inline="true">Prev</a>
        <a href="index.html" data-ajax="false" data-role="button" data-icon="home" data-inline="true">Home</a>
        <a href="#" data-ajax="false" data-role="button" data-icon="arrow-r" data-inline="true">Next</a>
    </div>
</div>
</div>''',
                  content, 0, re.MULTILINE | re.DOTALL)

def extract_id(pattern, content):
    return re.sub('<div id=\'' + pattern + '\'>.*?</div>', '', content, 0, re.MULTILINE | re.DOTALL)

def extract_class(pattern, content):
    return re.sub('<div class=\'' + pattern + '\'>.*?</div>', '', content, 0, re.MULTILINE | re.DOTALL)

def extract_message(pattern, content):
    return re.sub('<div id="' + pattern + '">.*?</div>', '', content, 0, re.MULTILINE | re.DOTALL)

def extract_spaces( content):
    return re.sub('^\s*$', '\n', content, 0, re.MULTILINE | re.DOTALL)

def extract_newlines(content):
    return re.sub('\n{2,}?', '', content, 0, re.MULTILINE | re.DOTALL)

def download_image(image):
    if not image:
        return
    [parent_dir, filename] = image.split('/')
    print 'parent_dir = %s, filename = %s' % (parent_dir, filename)
    if not os.path.exists(parent_dir) or not os.path.isdir(parent_dir):
        os.mkdir(parent_dir)
    url = IMAGE_BASE_URL + filename
    filepath = os.path.join(parent_dir, filename)
    urlretrieve(url, filepath)

def extract_images(content):
    images = re.findall('<img\s+src="(.+?)".*?>', content, re.IGNORECASE | re.MULTILINE)
    for img in images:
        download_image(img)

def changeDoc(content):
    return re.sub('<!DOCTYPE html.+?>', '<!DOCTYPE html>', content, 0, re.MULTILINE | re.DOTALL)

def removeScripts(content):
    return re.sub('<script.+?</script>', '', content, 0, re.MULTILINE | re.DOTALL)

def addJQuery(content):
    return re.sub('<link rel=\'alternate\'.+?/>',
                  '''<link rel="stylesheet" href="../jquery.mobile-1.4.0.min.css" type="text/css" charset="utf-8" />
    <script src="../jquery-1.10.2.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="../jquery.mobile-1.4.0.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="images/master.js" type="text/javascript" charset="utf-8"></script>''',
                  content, 0, 0)

def changeWrapper(content):
    return re.sub('<div id=\'wrapper\'>', '<div data-role="page">', content, 0, 0)

def changeContent(content):
    return re.sub('<div id=\'content\'>', '<div data-role="content">', content, 0, 0)

def process_file(html, out_dir):
    out_file = open(os.path.join(out_dir, html), 'w')
    in_file = open(html, 'r')
    content = in_file.read()
    in_file.close()
    new_content = changeHeader(content)
    new_content = changeFooter(new_content)
    new_content = extract_id('menu', new_content)
    new_content = extract_message('message', new_content)
    new_content = extract_id('nav', new_content)
    new_content = extract_class('clearfix', new_content)
    new_content = changeDoc(new_content)
    new_content = removeScripts(new_content)
    new_content = extract_spaces(new_content)
    new_content = extract_newlines(new_content)
    new_content = addJQuery(new_content)
    new_content = changeWrapper(new_content)
    new_content = changeContent(new_content)
    #extract_images(new_content)
    out_file.write(new_content)
    out_file.close()

def extract_file(out_dir, dirname, names):
    #print out_dir, dirname, names
    if dirname == '.':
        for html in names:
            if html.endswith('.html'):
                process_file(html, out_dir)

target_dir = '../assets/progit'
if not os.path.exists(target_dir):
    os.mkdir(target_dir)
os.path.walk('.', extract_file, target_dir)
