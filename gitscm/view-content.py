#!/usr/bin/env python

import json
import re

raw_content = json.load(open('gitscm.json', 'r'))

print "type of is ", type(raw_content)
chaps = []
for item in raw_content:
    title = ''.join(item['title'])
    link = ''.join(item['link'])
    filename = ''.join(item['filename'])
    if re.match('ch[1-9]-0', link, re.IGNORECASE) is not None:
        chap = {}
        chap['title'] = title
        chap['filename'] = filename
        chap['section'] = []
        chaps.append(chap)
    if link[0:3] == chaps[-1]['filename'][0:3]:
        sec = {}
        sec['title'] = title
        sec['filename'] = filename
        chaps[-1]['section'].append(sec)

# generate Java codes
print '\tList<ProGitChapter> chapters = new ArrayList<ProGitChapter>();'
chap_clazz = 'ProGitChapter '
sec_clazz = 'ProGitSection '
for c in chaps:
    print '\t%s chap = new ProGitChapter("%s");' % (chap_clazz, c['title'])
    for s in c['section']:
        print '\t%ssec = new ProGitSection("%s", "%s");' % (sec_clazz, s['title'], s['filename'])
        print '\tchap.addSection(sec);'
        sec_clazz = ''
    print '\tchapters.add(chap);'
    chap_clazz = ''

# generate index.html
#index = open('index.html', 'w')
print('''<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8">
    <title>Table of Contents</title>
    <link rel="stylesheet" href="../jquery.mobile-1.3.2.min.css" type="text/css" charset="utf-8" />
    <script src="../jquery-1.10.2.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="../jquery.mobile-1.3.2.min.js" type="text/javascript" charset="utf-8"></script>
  </head>''')
print('\n')
print('<body>\n')
print('<div data-role="collapsible-set">\n')
for c in chaps:
    print('    <div data-role="collapsible">\n')
    print('        <h3><a href="#">%s</a></h3>\n' % c['title'])
    print('        <div aria-hidden="true">\n')
    print('           <ul data-role="listview">\n')
    for s in c['section']:
        print('             <li><a href="%s">%s</a></li>\n' % (s['filename'], s['title']))
    print('           </ul>\n')
    print('        </div>\n')
    print('    </div>\n')
print('</div>')
print('\n')
print('</body>\n')
print('</html>')
#index.close()
