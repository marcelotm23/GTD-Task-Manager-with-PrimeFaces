package uo.sdi.business.impl.util;

import uo.sdi.business.exception.BusinessCheck;
import uo.sdi.business.exception.BusinessException;
import uo.sdi.dto.Category;
import uo.sdi.dto.User;
import uo.sdi.dto.types.UserStatus;
import uo.sdi.persistence.Persistence;

public class CategoryCheck {

	public static void nameIsNotNull(Category category)
			throws BusinessException {
		BusinessCheck.isNotNull(category.getName(),
				"Category name cannot be null");
	}

	public static void nameIsNotEmpty(Category category)
			throws BusinessException {
		BusinessCheck.isFalse(category.getName().length() == 0,
				"Category name cannot be empty");
	}

	public static void userIsNotNull(Category category)
			throws BusinessException {
		BusinessCheck.isNotNull(category.getUserId(),
				"A category must be assigned to a user");
	}

	public static void isValidUser(Category c) throws BusinessException {
		User u = Persistence.getUserDao().findById(c.getUserId());

		BusinessCheck.isNotNull(u,
				"A category must be assigned to an existing user");

		BusinessCheck.isTrue(u.getStatus().equals(UserStatus.ENABLED),
				"A category must be assigned to an enabled user");
	}

	public static void isUniqueName(Category category) throws BusinessException {
		Category other = Persistence.getCategoryDao().findByUserIdAndName(
				category.getUserId(), category.getName());

		BusinessCheck.isNull(other,
				"The category name cannot be repeated for this user");
	}

	public static void isNotForAdminUser(Category category)
			throws BusinessException {
		User u = Persistence.getUserDao().findById(category.getUserId());
		BusinessCheck
				.isFalse(u.getIsAdmin(), "An admin cannot have categories");
	}

}
