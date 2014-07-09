# -*- coding: utf-8 -*-
__author__ = 'eduardo'

import re
from HTMLParser import HTMLParser


class Parser(HTMLParser):

    def __init__(self):
        HTMLParser.__init__(self)
        self.recording = False
        self.data_aux = ''
        self.tabla = False
        self.heading = False
        self.data = ''

    def restart(self):
        self.recording = False
        self.data_aux = ''
        self.tabla = False
        self.heading = False
        self.data = ''

    def format(self):
        self.data = re.sub(r'4a', '4A', self.data)
        self.data = re.sub(r'linea', 'Linea ', self.data)

    def write_to_file(self, file):
        self.format()
        open_file = open(file, 'w')
        open_file.write(self.data)
        open_file.close()

    def handle_starttag(self, tag, attrs):
        if tag == 'table':
            self.table(attrs)
        elif self.tabla and tag == 'td':
            self.td()
        elif tag == 'a':
            self.a(attrs)

    def handle_endtag(self, tag):
        if tag == 'table':
            self.tabla = False
        elif tag == 'tr':
            if self.data_aux is not '':
                self.data += (self.data_aux + '\n')
            self.data_aux = ''
            self.recording = False
        elif tag == 'td':
            self.recording = False

    def handle_data(self, data):
        if self.recording:
            self.data_aux += data.strip()

    # Handle para los tag html
    def table(self, attrs):
        d_attrs = dict(attrs)

        if 'class' in d_attrs and 'id' in d_attrs:
            if d_attrs['class'] == 'schedule':
                self.tabla = True

    def td(self):
        self.recording = True
        if self.data_aux is not '':
            self.data_aux += '\t'

    def a(self, attrs):
        d_attrs = dict(attrs)

        if 'class' in d_attrs and d_attrs['class'] == 'trigger':
            # parseamos las entradas
            linea = re.search(r'\#(.*)\'..show', d_attrs['onclick'], flags=0).group(1)
            s1 = re.search(r'\#s1...html..([^\']*)', d_attrs['onclick'], flags=0).group(1)
            s2 = re.search(r'\#s2...html..([^\']*)', d_attrs['onclick'], flags=0).group(1)
            e1 = re.search(r'\#e1...html..([^\']*)', d_attrs['onclick'], flags=0).group(1)
            e2 = re.search(r'\#e2...html..([^\']*)', d_attrs['onclick'], flags=0).group(1)

            self.data += (linea + '\n' + 'Apertura\t' + s1 + '\t' + s2 + '\t' + 'Estación\t' + e1 + '\t'
                          + e2 + '\t' + 'Cierre\n')