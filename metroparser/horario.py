__author__ = 'eduardo'
# -*- coding: utf-8 -*-

import urllib2
from parser import Parser

file_extension = 'cvs'
urlToParse = [{'url': 'http://www.metrosantiago.cl/guia-viajero/horarios', 'file': 'horario'},
              {'url': 'http://www.metrosantiago.cl/guia-viajero/horarios/s', 'file': 'horario_sabado'},
              {'url': 'http://www.metrosantiago.cl/guia-viajero/horarios/df', 'file': 'horario_domingo'}]
parser = Parser()

for urlFile in urlToParse:
    f = urllib2.urlopen(urlFile['url'])
    html = f.read()
    parser.feed(html)
    parser.write_to_file(urlFile['file'] + '.' + file_extension)
    parser.restart()

parser.close()

print 'Extracción de datos exitosa'