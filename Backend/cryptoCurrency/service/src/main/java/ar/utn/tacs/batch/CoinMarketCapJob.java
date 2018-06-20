package ar.utn.tacs.batch;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import ar.utn.tacs.service.external.ExternalService;
import ar.utn.tacs.util.BeanUtil;

public class CoinMarketCapJob extends QuartzJobBean {

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		BeanUtil.getBean(ExternalService.class).updateCoinMarketCap();
	}
}
