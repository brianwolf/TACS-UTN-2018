package ar.utn.tacs.service.test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ar.utn.tacs.model.user.User;
import ar.utn.tacs.service.admin.AdminService;

public class AdminServiceTestSuite extends ServiceTestSuite{
	
	@Autowired
	AdminService adminService;
	
	@Test
	public void tostadoGanaBalanceContraLobezzznoPorqueEsLaPostinha() {
		String nickTostado = getLoginTostado().getNick(); 
		String nickLobezzzno = getLoginLobezzzno().getNick();
		
		User userGanador = adminService.compareBalance(nickTostado,nickLobezzzno);
		
		Assert.assertEquals(userGanador.getId(),getUserTostadoPosta().getId());
	}
	
	@Test
	public void tostadoEsElUnicoQueHizoTransaccionesEnLaSemana() {
		
		BigInteger transaccionesHastaHaceUnosSeg = adminService.statesLastWeek();
		
		tostadoCompraBitcoin(new BigDecimal(1));
		
		BigInteger transacccionesAhora = adminService.statesLastWeek();
		
		Assert.assertEquals(transaccionesHastaHaceUnosSeg,new BigInteger("0"));
		Assert.assertEquals(transacccionesAhora,new BigInteger("1"));
	}
	
	@Test
	public void tostadoEsElUnicoQueHizoTransaccionesEnElMes() {
		
		BigInteger transaccionesHastaHaceUnosSeg = adminService.statesLastMonth();
		
		tostadoCompraBitcoin(new BigDecimal(1));
		
		BigInteger transacccionesAhora = adminService.statesLastMonth();
		
		Assert.assertEquals(new BigInteger("0"),transaccionesHastaHaceUnosSeg);
		Assert.assertEquals(new BigInteger("1"),transacccionesAhora);
	}
	
	@Test
	public void tostadoEsElUnicoQueHizoTransaccionesDesdeElOrigenDeLosTiempos() {
		
		BigInteger transaccionesHastaHaceUnosSeg = adminService.statesAll();
		
		tostadoCompraBitcoin(new BigDecimal(1));
		
		BigInteger transacccionesAhora = adminService.statesAll();
		
		Assert.assertEquals(transaccionesHastaHaceUnosSeg,new BigInteger("0"));
		Assert.assertEquals(transacccionesAhora,new BigInteger("1"));
	}
	
	@Test
	public void obtenerNicksDeTostadoYLobezzzno() {
		
		String nickTostado = getLoginTostado().getNick(); 
		String nickLobezzzno = getLoginLobezzzno().getNick();
		
		List<String> nicksQueDeberianEstar = new ArrayList<String>();
		nicksQueDeberianEstar.add(nickTostado);
		nicksQueDeberianEstar.add(nickLobezzzno);
		
		Assert.assertTrue(adminService.getUsersNickAll().containsAll(nicksQueDeberianEstar));
	}
	
}
