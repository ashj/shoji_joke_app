package com.shoji.example.android.jokes.test;

import com.shoji.example.android.javajokes.Joker;
import org.junit.Test;

public class JokerTest {
    @Test
    public void test() {
        Joker joker = new Joker();
        assert joker.getJoke().length() != 0;
    }
}

