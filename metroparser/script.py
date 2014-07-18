__author__ = 'eduardo'
# -*- coding: utf-8 -*-

from database import AppDatabase


data = AppDatabase()
data.create_database()
data.add_lines()
data.add_station()
data.add_schedule(['horario.csv', 'horario_sabado.csv', 'horario_domingo.csv'])
data.close()
