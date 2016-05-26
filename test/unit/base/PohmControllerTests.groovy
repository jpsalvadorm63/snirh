package base



import org.junit.*
import grails.test.mixin.*

@TestFor(PohmController)
@Mock(Pohm)
class PohmControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/pohm/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.pohmInstanceList.size() == 0
        assert model.pohmInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.pohmInstance != null
    }

    void testSave() {
        controller.save()

        assert model.pohmInstance != null
        assert view == '/pohm/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/pohm/show/1'
        assert controller.flash.message != null
        assert Pohm.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/pohm/list'

        populateValidParams(params)
        def pohm = new Pohm(params)

        assert pohm.save() != null

        params.id = pohm.id

        def model = controller.show()

        assert model.pohmInstance == pohm
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/pohm/list'

        populateValidParams(params)
        def pohm = new Pohm(params)

        assert pohm.save() != null

        params.id = pohm.id

        def model = controller.edit()

        assert model.pohmInstance == pohm
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/pohm/list'

        response.reset()

        populateValidParams(params)
        def pohm = new Pohm(params)

        assert pohm.save() != null

        // test invalid parameters in update
        params.id = pohm.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/pohm/edit"
        assert model.pohmInstance != null

        pohm.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/pohm/show/$pohm.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        pohm.clearErrors()

        populateValidParams(params)
        params.id = pohm.id
        params.version = -1
        controller.update()

        assert view == "/pohm/edit"
        assert model.pohmInstance != null
        assert model.pohmInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/pohm/list'

        response.reset()

        populateValidParams(params)
        def pohm = new Pohm(params)

        assert pohm.save() != null
        assert Pohm.count() == 1

        params.id = pohm.id

        controller.delete()

        assert Pohm.count() == 0
        assert Pohm.get(pohm.id) == null
        assert response.redirectedUrl == '/pohm/list'
    }
}
