import sys

def part1():
    print(sum([2**(len(set(l.split(':')[1].split('|')[0].split()).intersection(set(l.split(':')[1].split('|')[1].split())))-1) for l in open(sys.argv[1], 'r') if len(set(l.split(':')[1].split('|')[0].split()).intersection(set(l.split(':')[1].split('|')[1].split()))) > 0]))

def part2():
    m = [len(set(l.split(':')[1].split('|')[0].split()).intersection(set(l.split(':')[1].split('|')[1].split()))) for l in open(sys.argv[1], 'r')]
    print(m)
    for i in range(1, len(m)):
        for j in range(0, m[i]):
            if i+j < len(m):
                m[i+j] = m[i+j] + m[i]
    print(m)
   

if __name__ == '__main__':
    part2()