import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { KhacPhucLoiComponentsPage, KhacPhucLoiDeleteDialog, KhacPhucLoiUpdatePage } from './khac-phuc-loi.page-object';

const expect = chai.expect;

describe('KhacPhucLoi e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let khacPhucLoiComponentsPage: KhacPhucLoiComponentsPage;
  let khacPhucLoiUpdatePage: KhacPhucLoiUpdatePage;
  let khacPhucLoiDeleteDialog: KhacPhucLoiDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load KhacPhucLois', async () => {
    await navBarPage.goToEntity('khac-phuc-loi');
    khacPhucLoiComponentsPage = new KhacPhucLoiComponentsPage();
    await browser.wait(ec.visibilityOf(khacPhucLoiComponentsPage.title), 5000);
    expect(await khacPhucLoiComponentsPage.getTitle()).to.eq('ctApp.khacPhucLoi.home.title');
  });

  it('should load create KhacPhucLoi page', async () => {
    await khacPhucLoiComponentsPage.clickOnCreateButton();
    khacPhucLoiUpdatePage = new KhacPhucLoiUpdatePage();
    expect(await khacPhucLoiUpdatePage.getPageTitle()).to.eq('ctApp.khacPhucLoi.home.createOrEditLabel');
    await khacPhucLoiUpdatePage.cancel();
  });

  it('should create and save KhacPhucLois', async () => {
    const nbButtonsBeforeCreate = await khacPhucLoiComponentsPage.countDeleteButtons();

    await khacPhucLoiComponentsPage.clickOnCreateButton();
    await promise.all([
      khacPhucLoiUpdatePage.setMaKPInput('maKP'),
      khacPhucLoiUpdatePage.setTenKPInput('tenKP'),
      khacPhucLoiUpdatePage.setNguoiTaoInput('nguoiTao'),
      khacPhucLoiUpdatePage.setNgayTaoInput('ngayTao'),
      khacPhucLoiUpdatePage.setUserIdInput('5')
    ]);
    expect(await khacPhucLoiUpdatePage.getMaKPInput()).to.eq('maKP', 'Expected MaKP value to be equals to maKP');
    expect(await khacPhucLoiUpdatePage.getTenKPInput()).to.eq('tenKP', 'Expected TenKP value to be equals to tenKP');
    expect(await khacPhucLoiUpdatePage.getNguoiTaoInput()).to.eq('nguoiTao', 'Expected NguoiTao value to be equals to nguoiTao');
    expect(await khacPhucLoiUpdatePage.getNgayTaoInput()).to.eq('ngayTao', 'Expected NgayTao value to be equals to ngayTao');
    expect(await khacPhucLoiUpdatePage.getUserIdInput()).to.eq('5', 'Expected userId value to be equals to 5');
    await khacPhucLoiUpdatePage.save();
    expect(await khacPhucLoiUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await khacPhucLoiComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last KhacPhucLoi', async () => {
    const nbButtonsBeforeDelete = await khacPhucLoiComponentsPage.countDeleteButtons();
    await khacPhucLoiComponentsPage.clickOnLastDeleteButton();

    khacPhucLoiDeleteDialog = new KhacPhucLoiDeleteDialog();
    expect(await khacPhucLoiDeleteDialog.getDialogTitle()).to.eq('ctApp.khacPhucLoi.delete.question');
    await khacPhucLoiDeleteDialog.clickOnConfirmButton();

    expect(await khacPhucLoiComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
