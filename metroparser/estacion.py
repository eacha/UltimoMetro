__author__ = 'eduardo'
# -*- coding: utf-8 -*-

import csv

line_elements = 7
name_station = 3


class Estacion():

    def __init__(self):
        self.file = 'estacion.csv'
        self.set = set()
        self.delimiter = '\t'

    def set_file(self, file):
        self.file = file

    def set_delimeter(self, delimiter):
        self.delimiter = delimiter

    def reset(self):
        self.set = set()

    def extract(self, file):
        open_file = open(file, 'rb')
        csv_reader = csv.reader(open_file, delimiter=self.delimiter)

        for row in csv_reader:
            if len(row) != line_elements:
                continue
            self.set.add(row.pop(name_station))
        open_file.close()

    def write_to_file(self):
        open_file = open(self.file, 'w')

        for station in self.set:
            open_file.write(station + '\n')

        open_file.close()