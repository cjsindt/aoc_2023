import sys

def part1():
    print(sum([2**(len(set(l.split(':')[1].split('|')[0].split()).intersection(set(l.split(':')[1].split('|')[1].split())))-1) for l in open(sys.argv[1], 'r') if len(set(l.split(':')[1].split('|')[0].split()).intersection(set(l.split(':')[1].split('|')[1].split()))) > 0]))

if __name__ == '__main__':
    part1()