package Dao_Test;

import com.poodel.database_manager.table_entity.Expenses;
import com.poodel.database_manager.table_entity.ExpensesServiceDAO;
import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

public class ExpensesServiceDAOTest {
    @Test
    public void testGetAll() throws SQLException{
        ExpensesServiceDAO expensesServiceDAO = new ExpensesServiceDAO();
        List<Expenses> list = expensesServiceDAO.getAll();
        Assert.assertNotNull(list);
        for (Expenses expenses : list){
            System.out.println(expenses);
        }

    }
}
