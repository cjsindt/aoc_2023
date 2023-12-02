RED = 12
GREEN = 13
BLUE = 14

# returns True if possible, else False
def determine_possibility(game):
    for s in game:
        for c in game[s]:
            if c == 'red':
                if game[s][c] > RED:
                    return False
            if c == 'green':
                if game[s][c] > GREEN:
                    return False
            if c == 'blue':
                if game[s][c] > BLUE:
                    return False
    
    return True

def main():
    games = {}
    total = 0

    # parse input
    with open('input.txt', 'r') as f:
        for l in f:
            game_id = int(l.split(':')[0].split()[1])
            games[game_id] = {}

            # each set pulled from the bag each game
            set_num = 1
            for s in l.split(':')[1].split(';'):
                games[game_id][set_num] = {k : int(v) for v, k in [x.split() for x in s.split(',')]}
                set_num = set_num + 1

    # determine impossible games
    for g in games:
        if determine_possibility(games[g]):
            total = total + g

    print(total)


if __name__ == '__main__':
    main()
