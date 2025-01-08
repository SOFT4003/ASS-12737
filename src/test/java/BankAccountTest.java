import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {

    /**
     * Kiểm tra khởi tạo tài khoản với số dư dương
     */
    @Test
    void testInitialBalance() {
        BankAccount account = new BankAccount("John Doe", 1000.0);
        assertEquals(1000.0, account.getBalance());
        assertEquals("John Doe", account.getOwner());
    }

    /**
     * Kiểm tra ngoại lệ khi khởi tạo tài khoản với số dư âm
     */
    @Test
    void testInitialBalanceNegative() {
        assertThrows(IllegalArgumentException.class, () -> new BankAccount("Invalid User", -500.0));
    }

    /**
     * Kiểm tra hàm deposit:
     * - Nạp tiền hợp lệ
     * - Nạp tiền bằng 0
     * - Nạp tiền âm
     */
    @Test
    void testDeposit() {
        BankAccount account = new BankAccount("Jane Doe", 500.0);

        // Nạp tiền hợp lệ
        account.deposit(200.0);
        assertEquals(700.0, account.getBalance());

        // Nạp 0 -> phải ném IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> account.deposit(0));

        // Nạp số âm -> phải ném IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> account.deposit(-100));
    }

    /**
     * Kiểm tra hàm withdraw:
     * - Rút tiền hợp lệ
     * - Rút 0
     * - Rút số âm
     * - Rút vượt số dư
     */
    @Test
    void testWithdraw() {
        BankAccount account = new BankAccount("John Doe", 1000.0);

        // Rút tiền hợp lệ
        account.withdraw(300.0);
        assertEquals(700.0, account.getBalance());

        // Rút 0 -> phải ném IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> account.withdraw(0));

        // Rút số âm -> phải ném IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> account.withdraw(-50));

        // Rút vượt số dư -> phải ném RuntimeException (Insufficient funds)
        assertThrows(RuntimeException.class, () -> account.withdraw(2000));
    }

    /**
     * Kiểm tra phương thức getBalance và getOwner
     */
    @Test
    void testGetBalanceAndOwner() {
        BankAccount account = new BankAccount("Jane Doe", 800.0);
        assertEquals(800.0, account.getBalance());
        assertEquals("Jane Doe", account.getOwner());
    }
}
