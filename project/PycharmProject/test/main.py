T = int(input())

for _ in range(T):
    A = [[0], [0]]
    n = int(input())
    dp = [[0 for _ in range(n+1)] for _ in range(2)]

    A[0] += list(map(int, input().split()))
    A[1] += list(map(int, input().split()))

    dp[0][1] = A[0][1]
    dp[1][1] = A[1][1]

    if(n>1):

        dp[1][2] = A[0][1]+A[1][2]
        dp[0][2] = A[1][1]+A[0][2]


    for i in range(3, n+1):
        max1 = dp[0][i-3] + A[1][i - 2]+A[0][i - 1]+A[1][i]
        max2 = dp[0][i-3] + A[1][i - 2]+A[0][i]
        max3 = dp[1][i-3] + A[0][i - 2]+A[1][i - 1]+A[0][i]
        max4 = dp[1][i-3] + A[0][i - 2]+A[1][i]
        max5 = max(dp[0][i-3], dp[1][i-3]) + A[1][i-1]+A[0][i]
        max6 = max(dp[0][i-3], dp[1][i-3]) + A[0][i-1]+A[1][i]

        dp[0][i] = max(dp[0][i - 1], max(max2, max3, max5))
        dp[1][i] = max(dp[1][i - 1], max(max1, max4, max6))


    print(max(dp[0][n], dp[1][n]))