package base



import org.junit.*
import grails.test.mixin.*

@TestFor(ParametroController)
@Mock(Parametro)
class ParametroControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/parametro/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.parametroInstanceList.size() == 0
        assert model.parametroInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.parametroInstance != null
    }

    void testSave() {
        controller.save()

        assert model.parametroInstance != null
        assert view == '/parametro/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/parametro/show/1'
        assert controller.flash.message != null
        assert Parametro.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/parametro/list'

        populateValidParams(params)
        def parametro = new Parametro(params)

        assert parametro.save() != null

        params.id = parametro.id

        def model = controller.show()

        assert model.parametroInstance == parametro
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/parametro/list'

        populateValidParams(params)
        def parametro = new Parametro(params)

        assert parametro.save() != null

        params.id = parametro.id

        def model = controller.edit()

        assert model.parametroInstance == parametro
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/parametro/list'

        response.reset()

        populateValidParams(params)
        def parametro = new Parametro(params)

        assert parametro.save() != null

        // test invalid parameters in update
        params.id = parametro.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/parametro/edit"
        assert model.parametroInstance != null

        parametro.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/parametro/show/$parametro.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        parametro.clearErrors()

        populateValidParams(params)
        params.id = parametro.id
        params.version = -1
        controller.update()

        assert view == "/parametro/edit"
        assert model.parametroInstance != null
        assert model.parametroInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/parametro/list'

        response.reset()

        populateValidParams(params)
        def parametro = new Parametro(params)

        assert parametro.save() != null
        assert Parametro.count() == 1

        params.id = parametro.id

        controller.delete()

        assert Parametro.count() == 0
        assert Parametro.get(parametro.id) == null
        assert response.redirectedUrl == '/parametro/list'
    }
}
