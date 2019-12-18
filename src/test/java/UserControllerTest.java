import static org.junit.Assert.*;

import org.junit.Test;
import com.pyt.uh.controller.UserController;
public class UserControllerTest {

	@Test
	public void frm_test() {
		UserController msnt = new UserController();
        assertNotNull(msnt.functions_Role_Mapping(1));
    }
	@Test
	public void policy_test() {
		UserController msnt = new UserController();
        assertNotNull(msnt.getPolicy(2,2));
    }
}
