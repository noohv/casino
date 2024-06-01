package dev.huuns.chat.plinko;

import java.util.ArrayList;

public record Plinko(
        Double point,
        Double multiplier,
        ArrayList<Character> pattern
) {
}
