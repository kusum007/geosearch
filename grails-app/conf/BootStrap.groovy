import com.kusom.geo.support.ConfigurationFactory

class BootStrap {

    def init = { servletContext ->
        ConfigurationFactory.init()
    }
    def destroy = {
    }
}
