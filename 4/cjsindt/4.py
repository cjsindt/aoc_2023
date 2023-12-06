import sys

def part1():
    print(sum([2**(len(set(l.split(':')[1].split('|')[0].split()).intersection(set(l.split(':')[1].split('|')[1].split())))-1) for l in open(sys.argv[1], 'r') if len(set(l.split(':')[1].split('|')[0].split()).intersection(set(l.split(':')[1].split('|')[1].split()))) > 0]))

def part2():
    # list of lists; inner list: [copies, winning #s]
    m = [[1, len(set(l.split(':')[1].split('|')[0].split()).intersection(set(l.split(':')[1].split('|')[1].split())))] for l in open(sys.argv[1], 'r')]
    for c in range(0, len(m)):
        for w in range(1, m[c][1]+1):
            m[c + w][0] = m[c + w][0] + m[c][0]
    total_cards = 0
    for c in m:
        total_cards = total_cards + c[0]
    
    print(total_cards)

if __name__ == '__main__':
    part1()
    part2()