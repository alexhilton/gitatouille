#!/usr/bin/env python

import json
import re
import io

def generate_content_list(chaps):
    contents = []
    for c in chaps:
        for s in c['section']:
            filename = str(s['filename'])
            contents.append(filename)
    print contents
    return contents

def generate_content_dict():
    raw_chaps = generate_chaps()
    chaps = generate_content_list(raw_chaps)
    contents = {}
    i = 0
    for c in chaps:
        prev = chaps[i]
        if i > 0:
            prev = chaps[i-1]
        next = chaps[i]
        if i < len(chaps) -1:
            next = chaps[i+1]
        contents[c] = {"prev": prev, "next": next}
        i += 1

    print contents
    return contents

def generate_java_code(chaps):
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

def encode(text):
    return text.encode('utf8')

def generate_index(chaps):
    target = '../assets/progit/index.html'
    index = io.open(target, 'w', encoding = 'utf8')
    index.write(unicode('''<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8">
    <title>Table of Contents</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="images/master.css" type="text/css" media="screen" charset="utf-8"/>
    <link rel="stylesheet" href="../jquery.mobile-1.4.0.min.css" type="text/css" charset="utf-8" />
    <script src="../jquery-1.10.2.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="../jquery.mobile-1.4.0.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="images/master.js" type="text/javascript" charset="utf-8"></script>
</head>\n'''))
    index.write(unicode('<body>\n'))
    index.write(unicode('<div data-role="page" id="home">\n'))
    index.write(unicode('''<div data-role="header" data-theme="b" data-position="fixed">
    <div data-role="navbar">
        <ul>
            <li><a href="#home" data-ajax="false" data-icon="home" class="ui-btn-active">Home</a></li>
            <li><a href="#about" data-ajax="false" data-icon="info">About</a></li>
            <li><a href="#contact" data-ajax="false" data-icon="star">Contact</a></li>
        </ul>
    </div>
</div>'''))
    index.write(unicode('<div data-role="content">\n'))
    index.write(unicode('<div data-role="collapsible-set">\n'))
    for c in chaps:
        index.write(unicode('    <div data-role="collapsible">\n'))
        index.write(unicode('        <h3><a href="#">%s</a></h3>\n' % c['title']))
        index.write(unicode('        <div aria-hidden="true">\n'))
        index.write(unicode('           <ul data-role="listview" data-theme="f" data-dividertheme="f">\n'))
        for s in c['section']:
            index.write(unicode('             <li><a href="%s" data-ajax="false" data-transition="slideup">%s</a></li>\n' % (s['filename'], s['title'])))
        index.write(unicode('           </ul>\n'))
        index.write(unicode('        </div>\n'))
        index.write(unicode('    </div>\n'))
    index.write(unicode('</div>\n'))
    index.write(unicode('</div>\n'))
    index.write(unicode('''<div data-role="footer" data-theme="b" data-position="fixed">
    <h2>Copyright</h2>
</div>\n'''))
    index.write(unicode('</div>\n'))
    index.write(unicode('''<div data-role="page" id="about">
    <div data-role="header" data-theme="b" data-position="fixed">
        <div data-role="navbar">
            <ul>
                <li><a href="#home" data-ajax="false" data-icon="home">Home</a></li>
                <li><a href="#about" data-ajax="false" data-icon="info" class="ui-btn-active">About</a></li>
                <li><a href="#contact" data-ajax="false" data-icon="star">Contact</a></li>
            </ul>
        </div>
    </div>
    <div data-role="content">
        <p>Where there is a will, there is a way.</p>
    </div>
</div>\n'''))
    index.write(unicode('''<div data-role="page" id="contact">
    <div data-role="header" data-theme="b" data-position="fixed">
        <div data-role="navbar">
            <ul>
                <li><a href="#home" data-ajax="false" data-icon="home">Home</a></li>
                <li><a href="#about" data-ajax="false" data-icon="info">About</a></li>
                <li><a href="#contact" data-ajax="false" data-icon="star" class="ui-btn-active">Contact</a></li>
            </ul>
        </div>
    </div>
    <div data-role="content">
        <p>Freedom is nothing but a chance to be better.</p>
    </div>
</div>\n'''))
    index.write(unicode('</body>\n'))
    index.write(unicode('</html>'))
    index.close()

def generate_chaps():
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

    return chaps

if __name__ == '__main__':
    chaps = generate_chaps()
    generate_index(chaps)
    content_list = generate_content_list(chaps)
    generate_content_dict()