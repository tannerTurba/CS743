import org.junit.Assert;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertAll;

/**
 * Pairwise Testing
 * Tanner Turba
 * 9/13/2023
 * This assignment aims to give experience in creating a test suite using
 * the ACTS test tool by NIST.
 */
public class PairwiseTesting {
    private Blackbox blackbox = new Blackbox();

    // @Test
    // public void allAssertions() {
    //     Assertions.assertAll(
    //         () -> Assert.assertNull(blackbox.importantFunction(5, 300, 1, false)),
    //         () -> Assert.assertEquals(" Everything looks good! ", blackbox.importantFunction(10, 400, 1, true))
    //     );
    // }

    @Test
    public void importantFunction_TestCaseOne_ReturnsNull() {
        Assert.assertNull(blackbox.importantFunction(5, 300, 1, false));
    }

    @Test
    public void importantFunction_TestCaseTwo() {
        Assert.assertEquals(" Everything looks good! ", blackbox.importantFunction(10, 400, 1, true));
    }

    @Test
    public void importantFunction_TestCaseThree() {
        Assert.assertEquals(" Everything looks good! ", blackbox.importantFunction(15, 200, 1, false));
    }

    @Test
    public void importantFunction_TestCaseFour_ReturnsNull() {
        Assert.assertNull(blackbox.importantFunction(5, 400, 2, true));
    }

    @Test
    public void importantFunction_TestCaseFive() {
        Assert.assertEquals(" Everything looks good! ", blackbox.importantFunction(10, 200, 2, false));
    }

    @Test
    public void importantFunction_TestCaseSix() {
        Assert.assertEquals(" Everything looks good! ", blackbox.importantFunction(15, 300, 2, true));
    }

    @Test
    public void importantFunction_TestCaseSeven() {
        Assert.assertEquals(" Everything looks good! ", blackbox.importantFunction(5, 200, 3, true));
    }

    @Test
    public void importantFunction_TestCaseEight() {
        Assert.assertEquals(" Everything looks good! ", blackbox.importantFunction(10, 300, 3, false));
    }

    @Test
    public void importantFunction_TestCaseNine() {
        Assert.assertEquals(" Everything looks good! ", blackbox.importantFunction(15, 400, 3, false));
    }

    @Test
    public void importantFunction_TestCaseTen() {
        Assert.assertEquals(" Everything looks good! ", blackbox.importantFunction(5, 200, 4, true));
    }

    @Test
    public void importantFunction_TestCaseEleven() {
        Assert.assertEquals(" Everything looks good! ", blackbox.importantFunction(10, 300, 4, false));
    }

    @Test
    public void importantFunction_TestCaseTwelve() {
        Assert.assertEquals(" Everything looks good! ", blackbox.importantFunction(15, 400, 4, false));
    }

    @Test
    public void importantFunction_TestCaseThirteen() {
        Assert.assertEquals(" Everything looks good! ", blackbox.importantFunction(5, 200, 5, true));
    }

    @Test
    public void importantFunction_TestCaseFourteen() {
        Assert.assertEquals(" Everything looks good! ", blackbox.importantFunction(10, 300, 5, false));
    }

    @Test
    public void importantFunction_TestCaseFifteen() {
        Assert.assertEquals(" Everything looks good! ", blackbox.importantFunction(15, 400, 5, false));
    }
}
