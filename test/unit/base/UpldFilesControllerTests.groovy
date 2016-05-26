package base



import org.junit.*
import grails.test.mixin.*

@TestFor(UpldFilesController)
@Mock(UpldFiles)
class UpldFilesControllerTests {

  def populateValidParams(params) {
    assert params != null
    // TODO: Populate valid properties like...
    //params["name"] = 'someValidName'
  }

  void testIndex() {
    controller.index()
    assert "/upldFiles/list" == response.redirectedUrl
  }

  void testList() {

    def model = controller.list()

    assert model.upldFilesInstanceList.size() == 0
    assert model.upldFilesInstanceTotal == 0
  }

  void testCreate() {
    def model = controller.create()

    assert model.upldFilesInstance != null
  }

  void testSave() {
    controller.save()

    assert model.upldFilesInstance != null
    assert view == '/upldFiles/create'

    response.reset()

    populateValidParams(params)
    controller.save()

    assert response.redirectedUrl == '/upldFiles/show/1'
    assert controller.flash.message != null
    assert UpldFiles.count() == 1
  }

  void testShow() {
    controller.show()

    assert flash.message != null
    assert response.redirectedUrl == '/upldFiles/list'

    populateValidParams(params)
    def upldFiles = new UpldFiles(params)

    assert upldFiles.save() != null

    params.id = upldFiles.id

    def model = controller.show()

    assert model.upldFilesInstance == upldFiles
  }

  void testEdit() {
    controller.edit()

    assert flash.message != null
    assert response.redirectedUrl == '/upldFiles/list'

    populateValidParams(params)
    def upldFiles = new UpldFiles(params)

    assert upldFiles.save() != null

    params.id = upldFiles.id

    def model = controller.edit()

    assert model.upldFilesInstance == upldFiles
  }

  void testUpdate() {
    controller.update()

    assert flash.message != null
    assert response.redirectedUrl == '/upldFiles/list'

    response.reset()

    populateValidParams(params)
    def upldFiles = new UpldFiles(params)

    assert upldFiles.save() != null

    // test invalid parameters in update
    params.id = upldFiles.id
    //TODO: add invalid values to params object

    controller.update()

    assert view == "/upldFiles/edit"
    assert model.upldFilesInstance != null

    upldFiles.clearErrors()

    populateValidParams(params)
    controller.update()

    assert response.redirectedUrl == "/upldFiles/show/$upldFiles.id"
    assert flash.message != null

    //test outdated version number
    response.reset()
    upldFiles.clearErrors()

    populateValidParams(params)
    params.id = upldFiles.id
    params.version = -1
    controller.update()

    assert view == "/upldFiles/edit"
    assert model.upldFilesInstance != null
    assert model.upldFilesInstance.errors.getFieldError('version')
    assert flash.message != null
  }

  void testDelete() {
    controller.delete()
    assert flash.message != null
    assert response.redirectedUrl == '/upldFiles/list'

    response.reset()

    populateValidParams(params)
    def upldFiles = new UpldFiles(params)

    assert upldFiles.save() != null
    assert UpldFiles.count() == 1

    params.id = upldFiles.id

    controller.delete()

    assert UpldFiles.count() == 0
    assert UpldFiles.get(upldFiles.id) == null
    assert response.redirectedUrl == '/upldFiles/list'
  }
}
