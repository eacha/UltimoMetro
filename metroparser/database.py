__author__ = 'eduardo'
# -*- coding: utf-8 -*-

import sqlite3
import json


class AppDatabase():

    def __init__(self, name='database', extension='db'):
        self.database_name = name
        self.database_extension = extension
        self.conection = sqlite3.connect('./' + self.database_name + '.' + self.database_extension)
        self.conection.text_factory = str
        self.cursor = self.conection.cursor()

    def create_database(self, schema='./schema.sql'):
        fd = open(schema, 'r')
        script = fd.read()
        self.cursor.executescript(script)
        fd.close()

    def add_lines(self, data='./lineas.json'):
        fd = open(data, 'r')
        lineas = json.loads(fd.read())

        self.cursor.executemany('INSERT INTO LINEA (name, color, start, end, express) '
                                'VALUES ( :name, :color, :start, :end, :express)', lineas)
        self.conection.commit()
        fd.close()

    def add_station(self, data='./estacion.csv'):
        fd = open(data, 'r')

        for line in fd:
            self.cursor.execute('INSERT INTO ESTACION (name, position) VALUES (?, ?)', (line.rstrip(), None))
        self.conection.commit()
        fd.close()

    def close(self):
        self.conection.close()

a = AppDatabase()
a.create_database()
a.add_lines()
a.add_station()
a.close()
