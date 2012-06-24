import org.safaproject.safa.model.user.Role
import org.safaproject.safa.model.user.Roles

class BootStrap {

	def init = { servletContext ->
		def role = new Role(Roles.ROLE_USER)
		
		role.save()
		
    }
	
    def destroy = {
    }
}
