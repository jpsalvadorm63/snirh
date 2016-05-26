package base



import org.junit.*
import grails.test.mixin.*

@TestFor(TipoRellenoController)
@Mock(TipoRelleno)
class TipoRellenoControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/tipoRelleno/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.tipoRellenoInstanceList.size() == 0
        assert model.tipoRellenoInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.tipoRellenoInstance != null
    }

    void testSave() {
        controller.save()

        assert model.tipoRellenoInstance != null
        assert view == '/tipoRelleno/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/tipoRelleno/show/1'
        assert controller.flash.message != null
        assert TipoRelleno.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/tipoRelleno/list'

        populateValidParams(params)
        def tipoRelleno = new TipoRelleno(params)

        assert tipoRelleno.save() != null

        params.id = tipoRelleno.id

        def model = controller.show()

        assert model.tipoRellenoInstance == tipoRelleno
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/tipoRelleno/list'

        populateValidParams(params)
        def tipoRelleno = new TipoRelleno(params)

        assert tipoRelleno.save() != null

        params.id = tipoRelleno.id

        def model = controller.edit()

        assert model.tipoRellenoInstance == tipoRelleno
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/tipoRelleno/list'

        response.reset()

        populateValidParams(params)
        def tipoRelleno = new TipoRelleno(params)

        assert tipoRelleno.save() != null

        // test invalid parameters in update
        params.id = tipoRelleno.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/tipoRelleno/edit"
        assert model.tipoRellenoInstance != null

        tipoRelleno.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/tipoRelleno/show/$tipoRelleno.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        tipoRelleno.clearErrors()

        populateValidParams(params)
        params.id = tipoRelleno.id
        params.version = -1
        controller.update()

        assert view == "/tipoRelleno/edit"
        assert model.tipoRellenoInstance != null
        assert model.tipoRellenoInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/tipoRelleno/list'

        response.reset()

        populateValidParams(params)
        def tipoRelleno = new TipoRelleno(params)

        assert tipoRelleno.save() != null
        assert TipoRelleno.count() == 1

        params.id = tipoRelleno.id

        controller.delete()

        assert TipoRelleno.count() == 0
        assert TipoRelleno.get(tipoRelleno.id) == null
        assert response.redirectedUrl == '/tipoRelleno/list'
    }
}
