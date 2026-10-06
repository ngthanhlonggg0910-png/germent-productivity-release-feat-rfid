import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { ThongSoThucThiComponentsPage, ThongSoThucThiDeleteDialog, ThongSoThucThiUpdatePage } from './thong-so-thuc-thi.page-object';

const expect = chai.expect;

describe('ThongSoThucThi e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let thongSoThucThiComponentsPage: ThongSoThucThiComponentsPage;
  let thongSoThucThiUpdatePage: ThongSoThucThiUpdatePage;
  let thongSoThucThiDeleteDialog: ThongSoThucThiDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load ThongSoThucThis', async () => {
    await navBarPage.goToEntity('thong-so-thuc-thi');
    thongSoThucThiComponentsPage = new ThongSoThucThiComponentsPage();
    await browser.wait(ec.visibilityOf(thongSoThucThiComponentsPage.title), 5000);
    expect(await thongSoThucThiComponentsPage.getTitle()).to.eq('ctApp.thongSoThucThi.home.title');
  });

  it('should load create ThongSoThucThi page', async () => {
    await thongSoThucThiComponentsPage.clickOnCreateButton();
    thongSoThucThiUpdatePage = new ThongSoThucThiUpdatePage();
    expect(await thongSoThucThiUpdatePage.getPageTitle()).to.eq('ctApp.thongSoThucThi.home.createOrEditLabel');
    await thongSoThucThiUpdatePage.cancel();
  });

  it('should create and save ThongSoThucThis', async () => {
    const nbButtonsBeforeCreate = await thongSoThucThiComponentsPage.countDeleteButtons();

    await thongSoThucThiComponentsPage.clickOnCreateButton();
    await promise.all([
      thongSoThucThiUpdatePage.setSpIndexInput('spIndex'),
      thongSoThucThiUpdatePage.thongSoDoSPSizeSelectLastOption(),
      thongSoThucThiUpdatePage.dungSaiSelectLastOption(),
      thongSoThucThiUpdatePage.proKeHoachSanXuatSelectLastOption()
    ]);
    expect(await thongSoThucThiUpdatePage.getSpIndexInput()).to.eq('spIndex', 'Expected SpIndex value to be equals to spIndex');
    await thongSoThucThiUpdatePage.save();
    expect(await thongSoThucThiUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await thongSoThucThiComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last ThongSoThucThi', async () => {
    const nbButtonsBeforeDelete = await thongSoThucThiComponentsPage.countDeleteButtons();
    await thongSoThucThiComponentsPage.clickOnLastDeleteButton();

    thongSoThucThiDeleteDialog = new ThongSoThucThiDeleteDialog();
    expect(await thongSoThucThiDeleteDialog.getDialogTitle()).to.eq('ctApp.thongSoThucThi.delete.question');
    await thongSoThucThiDeleteDialog.clickOnConfirmButton();

    expect(await thongSoThucThiComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
