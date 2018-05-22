def getEval(formula):
    try:
        res = eval(t)
    except ValueError as err:
        res = 0
    return res