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
    print '\t%schap = new ProGitChapter("%s");' % (chap_clazz, c['title'])
    for s in c['section']:
        print '\t%ssec = new ProGitSection("%s", "%s");' % (sec_clazz, s['title'], s['filename'])
        print '\tchap.addSection(sec);'
        sec_clazz = ''
    print '\tchapters.add(chap);'
    chap_clazz = ''
