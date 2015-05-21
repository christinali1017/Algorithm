class Solution:

    # @param {string} s
    # @param {string} p
    # @return {boolean}
    def isMatch(self, s, p):
        # Boundary Check
        if not s and not p: return True
        if s and not p: return False

        # First get all pattern units
        # Each unit will be `c` `c*` `.` or `.*` while c represents a single character
        patterns = []
        for pos in xrange(len(p)):
            if p[pos] == "*":
                patterns[-1] += "*"  # Add `*` to the previous pattern.
                if len(patterns) > 1 and patterns[-1] == patterns[-2]:
                    del patterns[-1]
                continue
            patterns.append(p[pos])

        # A function that check if a pattern has a star
        def has_star(i):
            return patterns[i][-1] == "*"

        # Matching
        match_stack = [[], []]
        self.ps, self.pp = 0, 0

        # Comparesion functions
        def ps_less():
            return self.ps < len(s)

        def ps_eq():
            return self.ps == len(s)

        def pp_less():
            return self.pp < len(patterns)

        def pp_eq():
            return self.pp == len(patterns)

        # Top elements of the match_stack
        def top_ps():
            return match_stack[0][-1]

        def top_pp():
            return match_stack[1][-1]

        def top_ps_increase():
            match_stack[0][-1] += 1
            self.ps = top_ps() + 1
            self.pp = top_pp() + 1

        # Push to match_stack
        def push(ps, pp):
            match_stack[0].append(ps)
            match_stack[1].append(pp)

        def pop():
            match_stack[0].pop()
            match_stack[1].pop()

        # Rollback to match more characters.
        # In default, patterns with "*" will match as least as possible.
        # So when patterns don't match, it will rollback to make patterns with "*" to match
        # more characters.
        def rollback():
            if len(match_stack[0]) == 0:  # If there is no more "*" patterns, that means, not matching.
                return False  # Return false means, not matching
            if match_stack[0][-1] < len(s) - 1 and match(top_ps() + 1, top_pp()):
                top_ps_increase()  # Increase the position of s by 1, and set the ps and pp to next positions.
                return True  # Return true means, can continue matching
            pop()  # Not matching, then pop and compare the lower item in stack.
            return rollback()

        def match(ps, pp):
            if patterns[pp][0] == ".":
                return True
            return s[ps] == patterns[pp][0]

        while ps_less() or pp_less():
            if ps_eq() and pp_less():  # Matched source string.
                all_has_star = True    # If all patterns after this have "*", then match successful.
                for pp_remain in xrange(self.pp, len(patterns)):
                    if not has_star(pp_remain):
                        all_has_star = False
                        break
                if all_has_star: return True
                if not rollback(): return False  # Otherwise, rollback()
                continue

            elif ps_less() and pp_eq():
                if not rollback(): return False  # Not matching all source string, need rollback()
                continue

            elif patterns[self.pp][-1] == "*":
                push(self.ps - 1, self.pp)  # self.ps - 1 means, not matching any characters when
                                            # find "*" at the first time.
                                            # The matching of "*" will be done in rollback()
                                            # while each time it will match one more character.
                self.pp += 1
                continue

            elif match(self.ps, self.pp):  # Normal matching, the pattern without "*"
                self.ps += 1
                self.pp += 1
                continue

            if not rollback(): return False

        # When ps and pp all reach the end of s and patterns, they match
        return True
