package ar.utn.tacs.batch.user;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import ar.utn.tacs.service.user.UserService;
import ar.utn.tacs.util.BeanUtil;

public class LogoutUserJob extends QuartzJobBean{

	private static final String KEY_MINUTES_PARAM = "minutes";
	
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		Integer minutes = (Integer) context.getMergedJobDataMap().getIntValue(KEY_MINUTES_PARAM);
		BeanUtil.getBean(UserService.class).updateConectedUsersInServer(minutes);
	}

}
