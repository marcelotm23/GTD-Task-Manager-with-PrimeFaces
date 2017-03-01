package uo.sdi.business.impl.user.command;

import uo.sdi.business.exception.BusinessCheck;
import uo.sdi.business.exception.BusinessException;
import uo.sdi.business.impl.command.Command;
import uo.sdi.business.impl.util.UserCheck;
import uo.sdi.dto.User;
import uo.sdi.persistence.Persistence;
import uo.sdi.persistence.UserDao;

public class UpdateUserDetailsCommand implements Command<Void> {

	private User user;

	public UpdateUserDetailsCommand(User user) {
		this.user = user;
	}

	@Override
	public Void execute() throws BusinessException {
		UserDao uDao = Persistence.getUserDao();
		User previous = uDao.findById( user.getId() );

		checkUserExist( previous );
		if(user.getIsAdmin())
			checkStatusIsNotChanged( previous, user );
		checkIsAdminNotChanged( previous, user );
		UserCheck.isValidEmailSyntax( user ); 
		UserCheck.minLoginLength( user );
		UserCheck.minPasswordLength( user );
		
		if (loginIsChanged(previous, user) ) {
			UserCheck.notRepeatedLogin( user );
		}

		uDao.update( user );
		return null;
	}

	private void checkIsAdminNotChanged(User previous, User current)
			throws BusinessException {
		BusinessCheck.isTrue( isAdminNotChanged( previous, current ),
				"A user cannot be upgraded or downgraded" );
	}

	private void checkUserExist(User previous) throws BusinessException {
		BusinessCheck.isNotNull( previous, "The user does not exist");
	}

	private void checkStatusIsNotChanged(User previous, User current)
			throws BusinessException {
		BusinessCheck.isTrue( statusIsNotChanged(previous, current), 
				"No puedes cambiar el status de un admin");
	}

	private boolean statusIsNotChanged(User previous, User current) {
		return previous.getStatus().equals( current.getStatus() );
	}

	private boolean loginIsChanged(User previous, User current) {
		return ! previous.getLogin().equals( current.getLogin() );
	}

	private boolean isAdminNotChanged(User previous, User current) {
		return previous.getIsAdmin() == current.getIsAdmin();
	}

}
