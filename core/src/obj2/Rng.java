package obj2;

import com.badlogic.gdx.utils.NumberUtils;
public class Rng
{
    private long state0, state1;

    /**
     * Gets a roughly 96-bit random seed using Math.random().
     */
    public Rng() {
        this((int) ((Math.random() * 2.0 - 1.0) * 0x80000000),
                (int) ((Math.random() * 2.0 - 1.0) * 0x80000000),
                (int) ((Math.random() * 2.0 - 1.0) * 0x80000000));
    }

    /**
     * Takes 3 ints for seeds; this is more than enough state to allow for many different sequences, each with
     * a somewhat-low period of 2 to the 65.
     * @param seed0 any int, will not be used verbatim; might be best to avoid 0
     * @param seed1 any int, will not be used verbatim; might be best to avoid 0
     * @param seed2 any int, will not be used verbatim; might be best to avoid 0
     */
    public Rng(int seed0, int seed1, int seed2) {
        state0 = (seed0 * 421L + seed1 * 181L * seed2 << 20) ^ 0x8329C6EB9E6AD3E3L;
        state1 = (seed1 * 0x8329C6EB9E6AD3E3L ^ seed2 - 0xC6BC279692B5C483L) + seed0 * 0x9E3779B97F4A7C15L;
    }

    /**
     * @return a pseudo-random float between -1f and 1f, exclusive on both and much more likely to be near 0
     */
    public final float biasedFloat() {
        final long start = (state1 += ((state0 += 0x9E3779B97F4A7C15L) >> 24) * 0x632AE59B69B3C209L);
        return (NumberUtils.intBitsToFloat((int)start >>> 9 | 0x3F800000) - 1f)
                * (NumberUtils.intBitsToFloat((int)(start >>> 41) | 0x3F800000) - 1f)
                * (start >> 63 | 1);
    }
}