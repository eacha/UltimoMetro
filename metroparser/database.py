__author__ = 'eduardo'
# -*- coding: utf-8 -*-

import sqlite3
import json
import csv

line_elements = 7
open_st = 0
f_start = 1
f_end = 2
name_station = 3
l_start = 4
l_end = 5
close_st = 6


class AppDatabase():

    def __init__(self, name='database', extension='db'):
        self.database_name = name
        self.database_extension = extension
        self.conection = sqlite3.connect(self.database_name + '.' + self.database_extension)
        self.conection.text_factory = str
        self.cursor = self.conection.cursor()
        self.delimiter = '\t'

    def set_delimiter(self, delimiter):
        self.delimiter = delimiter

    def create_database(self, schema='schema.sql'):
        fd = open(schema, 'r')
        script = fd.read()
        self.cursor.executescript(script)
        fd.close()

    def add_lines(self, data='lineas.json'):
        fd = open(data, 'r')
        lineas = json.loads(fd.read())

        self.cursor.executemany('INSERT INTO linea (name, color, start, end, express) '
                                'VALUES ( :name, :color, :start, :end, :express)', lineas)
        self.conection.commit()
        fd.close()

    def add_station(self, data='horario.csv'):
        fd = open(data, 'r')
        csv_reader = csv.reader(fd, delimiter=self.delimiter)
        linea_id = None

        for row in csv_reader:
            if len(row) != line_elements:
                linea_id = (self.cursor.execute('SELECT id FROM linea WHERE name = ?', (row[0],)).fetchone())[0]
            else:
                estacion_name = row[3]

                if estacion_name != 'Estación':
                    self.cursor.execute('INSERT INTO estacion (name, linea) VALUES ( ?, ? )',
                                        (estacion_name, linea_id))

        self.conection.commit()
        fd.close()

    def add_schedule(self, data):
        type = 1
        for file in data:
            open_file = open(file, 'r')
            csv_reader = csv.reader(open_file, delimiter=self.delimiter)
            linea_id = None

            for row in csv_reader:

                if len(row) != line_elements:
                    linea_id = (self.cursor.execute('SELECT id FROM linea WHERE name = ?', (row[0],)).fetchone())[0]
                else:
                    estacion = row[name_station]

                    if estacion != 'Estación':
                        print estacion
                        estacion_id = (self.cursor.execute('SELECT id FROM estacion WHERE name = ? and linea = ?',
                                                           (estacion, linea_id)).fetchone())[0]
                        self.cursor.execute('INSERT INTO horario (estacion, type, open, close, first_start, last_start,'
                                            ' first_end, last_end) VALUES (?, ?, ?, ?, ?, ?, ?, ?)',
                                            (estacion_id, type, row[open_st], row[close_st], row[f_start], row[l_start],
                                             row[f_end], row[l_end]))

            self.conection.commit()
            open_file.close()
            type += 1

    def close(self):
        self.conection.close()

a = AppDatabase()
a.create_database()
a.add_lines()
a.add_station()
a.add_schedule(['horario.csv', 'horario_sabado.csv', 'horario_domingo.csv'])
a.close()
