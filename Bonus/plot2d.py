#!/usr/bin/env python2
# -*- coding: utf-8 -*-

import sys
import argparse
import csv

import numpy as np
import matplotlib.pyplot as plt

def csv_parse(filename):
    retlist = list()
    with open(filename, 'rb') as csvfile:
        reader = csv.reader(csvfile, delimiter=',')
        reader.next()
        for row in reader:
            retlist.append(map(float, row))
    return retlist

def main(files, show=False):
    for f in files:
        plot_values  = csv_parse(f)
        time = [values[0] for values in plot_values]
        ball1 = [values[-2] for values in plot_values]
        ball2 = [values[-1] for values in plot_values]
        
        plt.grid(True)
        plt.title('Block (green) | Ball (red)')
        plt.plot(time, ball1, 'g.-')
        plt.plot(time, ball2, 'r.-')
        plt.ylabel('position [m]')
        plt.xlabel('time [s]')

        plt.savefig(f.replace('.csv', '.png'))
        if show:
            plt.show()
    return 0

if __name__ == '__main__':
    parser = argparse.ArgumentParser(description='Creates 2-dimensional plots from csv files')
    parser.add_argument('files', metavar='FILE', type=str, nargs='+')
    parser.add_argument('-s', '--show', action='store_true')
    args = parser.parse_args()
    if args.show:
        retval = main(args.files, show=True)
    else:
        retval = main(args.files)
    sys.exit(retval)