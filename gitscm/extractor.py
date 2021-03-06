#!/usr/bin/env python

from urllib import urlretrieve
import os
import os.path
import re

IMAGE_BASE_URL = 'http://www.webarch.org/ProGit/images/'

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

def process_file(html, out_dir):
    out_file = open(os.path.join(out_dir, html), 'w')
    in_file = open(html, 'r')
    content = in_file.read()
    in_file.close()
    new_content = extract_id('header', content)
    new_content = extract_id('menu', new_content)
    new_content = extract_message('message', new_content)
    new_content = extract_id('nav', new_content)
    new_content = extract_id('footer', new_content)
    new_content = extract_class('clearfix', new_content)
    new_content = extract_spaces(new_content)
    new_content = extract_newlines(new_content)
    extract_images(new_content)
    out_file.write(new_content)
    out_file.close()

def extract_file(out_dir, dirname, names):
    #print out_dir, dirname, names
    if dirname == '.':
        for html in names:
            if html.endswith('.html'):
                process_file(html, out_dir)

os.path.walk('.', extract_file, 'progit')
