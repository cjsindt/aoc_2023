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


# return a tuple (red, green, blue) containing minimal values
def minimize_game(game):
    output = [0, 0, 0]
    for s in game:
        for c in game[s]:
            if c == 'red':
                if game[s][c] > output[0]:
                    output[0] = game[s][c]
            if c == 'green':
                if game[s][c] > output[1]:
                    output[1] = game[s][c]
            if c == 'blue':
                if game[s][c] > output[2]:
                    output[2] = game[s][c]

    return output

# determines the power of a game 
# i.e. the minimal values of red, green and blue multiplied together
def power(game):
    prod = 1
    for i in game:
        prod = prod * i

    return prod

def main():
    games = {}
    total_1 = 0
    total_2 = 0

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
            total_1 = total_1 + g

    print(f'Part 1: {total_1}')

    # determine minimal games
    for g in games:
        total_2 = total_2 + power(minimize_game(games[g]))

    print(f'Part 2: {total_2}')

if __name__ == '__main__':
    main()
