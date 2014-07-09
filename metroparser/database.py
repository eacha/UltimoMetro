__author__ = 'eduardo'
# -*- coding: utf-8 -*-

import sqlite3
import json
import csv

line_elements = 7


class AppDatabase():

    def __init__(self, name='database', extension='db'):
        self.database_name = name
        self.database_extension = extension
        self.conection = sqlite3.connect(self.database_name + '.' + self.database_extension)
        self.conection.text_factory = str
        self.cursor = self.conection.cursor()

    def create_database(self, schema='schema.sql'):
        fd = open(schema, 'r')
        script = fd.read()
        self.cursor.executescript(script)
        fd.close()

    def add_lines(self, data='lineas.json'):
        fd = open(data, 'r')
        lineas = json.loads(fd.read())

        self.cursor.executemany('INSERT INTO LINEA (name, color, start, end, express) '
                                'VALUES ( :name, :color, :start, :end, :express)', lineas)
        self.conection.commit()
        fd.close()

    def add_station(self, data='horario.csv'):
        fd = open(data, 'r')
        csv_reader = csv.reader(fd, delimiter='\t')
        linea_id = None

        for row in csv_reader:
            if len(row) != line_elements:
                linea_id = (self.cursor.execute('SELECT id FROM LINEA WHERE name = ?', (row[0],)).fetchone())[0]
            else:
                estacion_name = row[3]

                if estacion_name != 'Estación':
                    self.cursor.execute('INSERT INTO ESTACION (name, linea) VALUES ( ?, ? )',
                                        (estacion_name, linea_id))

        self.conection.commit()
        fd.close()

    def add_schedule(self, data):
        type = 1
        for file in data:
            open_file = open(file, 'r')
            csv_reader = csv.reader(open_file, delimiter='\t')
            linea_id = None

            for row in csv_reader:

                if len(row) != line_elements:
                    linea_id = (self.cursor.execute('SELECT id FROM LINEA WHERE name = ?', (row[0],)).fetchone())[0]
                else:
                    estacion = row[3]

                    if estacion != 'Estación':
                        print estacion
                        estacion_id = (self.cursor.execute('SELECT id FROM ESTACION WHERE name = ? and linea = ?',
                                                           (estacion, linea_id)).fetchone())[0]
                        self.cursor.execute('INSERT INTO HORARIO (estacion, type, open, close, first_start, last_start,'
                                            ' first_end, last_end) VALUES (?, ?, ?, ?, ?, ?, ?, ?)',
                                            (estacion_id, type, row[0], row[6], row[1], row[4], row[2], row[5]))

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
