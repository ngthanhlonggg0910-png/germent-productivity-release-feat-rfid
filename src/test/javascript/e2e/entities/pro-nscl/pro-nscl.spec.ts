import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { ProNSCLComponentsPage, ProNSCLDeleteDialog, ProNSCLUpdatePage } from './pro-nscl.page-object';

const expect = chai.expect;

describe('ProNSCL e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let proNSCLComponentsPage: ProNSCLComponentsPage;
  let proNSCLUpdatePage: ProNSCLUpdatePage;
  let proNSCLDeleteDialog: ProNSCLDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load ProNSCLS', async () => {
    await navBarPage.goToEntity('pro-nscl');
    proNSCLComponentsPage = new ProNSCLComponentsPage();
    await browser.wait(ec.visibilityOf(proNSCLComponentsPage.title), 5000);
    expect(await proNSCLComponentsPage.getTitle()).to.eq('ctApp.proNSCL.home.title');
    await browser.wait(ec.or(ec.visibilityOf(proNSCLComponentsPage.entities), ec.visibilityOf(proNSCLComponentsPage.noResult)), 1000);
  });

  it('should load create ProNSCL page', async () => {
    await proNSCLComponentsPage.clickOnCreateButton();
    proNSCLUpdatePage = new ProNSCLUpdatePage();
    expect(await proNSCLUpdatePage.getPageTitle()).to.eq('ctApp.proNSCL.home.createOrEditLabel');
    await proNSCLUpdatePage.cancel();
  });

  it('should create and save ProNSCLS', async () => {
    const nbButtonsBeforeCreate = await proNSCLComponentsPage.countDeleteButtons();

    await proNSCLComponentsPage.clickOnCreateButton();

    await promise.all([
      proNSCLUpdatePage.setTaiKhoanInput('taiKhoan'),
      proNSCLUpdatePage.setNgaySanXuatInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      proNSCLUpdatePage.setSttInput('5'),
      proNSCLUpdatePage.setMucTieuNgayInput('5'),
      proNSCLUpdatePage.setThucHienNgayInput('5'),
      proNSCLUpdatePage.setLoiNgayInput('5'),
      proNSCLUpdatePage.setSuaNgayInput('5'),
      proNSCLUpdatePage.setBtpCapInput('5'),
      proNSCLUpdatePage.setQty1hInput('5'),
      proNSCLUpdatePage.setQty2hInput('5'),
      proNSCLUpdatePage.setQty3hInput('5'),
      proNSCLUpdatePage.setQty4hInput('5'),
      proNSCLUpdatePage.setQty5hInput('5'),
      proNSCLUpdatePage.setQty6hInput('5'),
      proNSCLUpdatePage.setQty7hInput('5'),
      proNSCLUpdatePage.setQty8hInput('5'),
      proNSCLUpdatePage.setQty9hInput('5'),
      proNSCLUpdatePage.setQty10hInput('5'),
      proNSCLUpdatePage.setQty11hInput('5'),
      proNSCLUpdatePage.setQty12hInput('5'),
      proNSCLUpdatePage.setQty13hInput('5'),
      proNSCLUpdatePage.setQty14hInput('5'),
      proNSCLUpdatePage.setReject1hInput('5'),
      proNSCLUpdatePage.setReject2hInput('5'),
      proNSCLUpdatePage.setReject3hInput('5'),
      proNSCLUpdatePage.setReject4hInput('5'),
      proNSCLUpdatePage.setReject5hInput('5'),
      proNSCLUpdatePage.setReject6hInput('5'),
      proNSCLUpdatePage.setReject7hInput('5'),
      proNSCLUpdatePage.setReject8hInput('5'),
      proNSCLUpdatePage.setReject9hInput('5'),
      proNSCLUpdatePage.setReject10hInput('5'),
      proNSCLUpdatePage.setReject11hInput('5'),
      proNSCLUpdatePage.setReject12hInput('5'),
      proNSCLUpdatePage.setReject13hInput('5'),
      proNSCLUpdatePage.setReject14hInput('5'),
      proNSCLUpdatePage.setMan1hInput('5'),
      proNSCLUpdatePage.setMan2hInput('5'),
      proNSCLUpdatePage.setMan3hInput('5'),
      proNSCLUpdatePage.setMan4hInput('5'),
      proNSCLUpdatePage.setMan5hInput('5'),
      proNSCLUpdatePage.setMan6hInput('5'),
      proNSCLUpdatePage.setMan7hInput('5'),
      proNSCLUpdatePage.setMan8hInput('5'),
      proNSCLUpdatePage.setMan9hInput('5'),
      proNSCLUpdatePage.setMan10hInput('5'),
      proNSCLUpdatePage.setMan11hInput('5'),
      proNSCLUpdatePage.setMan12hInput('5'),
      proNSCLUpdatePage.setMan13hInput('5'),
      proNSCLUpdatePage.setMan14hInput('5'),
      proNSCLUpdatePage.setManOnly7HoursInput('5'),
      proNSCLUpdatePage.setFix1hInput('5'),
      proNSCLUpdatePage.setFix2hInput('5'),
      proNSCLUpdatePage.setFix3hInput('5'),
      proNSCLUpdatePage.setFix4hInput('5'),
      proNSCLUpdatePage.setFix5hInput('5'),
      proNSCLUpdatePage.setFix6hInput('5'),
      proNSCLUpdatePage.setFix7hInput('5'),
      proNSCLUpdatePage.setFix8hInput('5'),
      proNSCLUpdatePage.setFix9hInput('5'),
      proNSCLUpdatePage.setFix10hInput('5'),
      proNSCLUpdatePage.setFix11hInput('5'),
      proNSCLUpdatePage.setFix12hInput('5'),
      proNSCLUpdatePage.setFix13hInput('5'),
      proNSCLUpdatePage.setFix14hInput('5'),
      proNSCLUpdatePage.proKeHoachSanXuatSelectLastOption(),
      proNSCLUpdatePage.proCheckPointSelectLastOption()
    ]);

    expect(await proNSCLUpdatePage.getTaiKhoanInput()).to.eq('taiKhoan', 'Expected TaiKhoan value to be equals to taiKhoan');
    expect(await proNSCLUpdatePage.getNgaySanXuatInput()).to.contain(
      '2001-01-01T02:30',
      'Expected ngaySanXuat value to be equals to 2000-12-31'
    );
    expect(await proNSCLUpdatePage.getSttInput()).to.eq('5', 'Expected stt value to be equals to 5');
    expect(await proNSCLUpdatePage.getMucTieuNgayInput()).to.eq('5', 'Expected mucTieuNgay value to be equals to 5');
    expect(await proNSCLUpdatePage.getThucHienNgayInput()).to.eq('5', 'Expected thucHienNgay value to be equals to 5');
    expect(await proNSCLUpdatePage.getLoiNgayInput()).to.eq('5', 'Expected loiNgay value to be equals to 5');
    expect(await proNSCLUpdatePage.getSuaNgayInput()).to.eq('5', 'Expected suaNgay value to be equals to 5');
    expect(await proNSCLUpdatePage.getBtpCapInput()).to.eq('5', 'Expected btpCap value to be equals to 5');
    expect(await proNSCLUpdatePage.getQty1hInput()).to.eq('5', 'Expected qty1h value to be equals to 5');
    expect(await proNSCLUpdatePage.getQty2hInput()).to.eq('5', 'Expected qty2h value to be equals to 5');
    expect(await proNSCLUpdatePage.getQty3hInput()).to.eq('5', 'Expected qty3h value to be equals to 5');
    expect(await proNSCLUpdatePage.getQty4hInput()).to.eq('5', 'Expected qty4h value to be equals to 5');
    expect(await proNSCLUpdatePage.getQty5hInput()).to.eq('5', 'Expected qty5h value to be equals to 5');
    expect(await proNSCLUpdatePage.getQty6hInput()).to.eq('5', 'Expected qty6h value to be equals to 5');
    expect(await proNSCLUpdatePage.getQty7hInput()).to.eq('5', 'Expected qty7h value to be equals to 5');
    expect(await proNSCLUpdatePage.getQty8hInput()).to.eq('5', 'Expected qty8h value to be equals to 5');
    expect(await proNSCLUpdatePage.getQty9hInput()).to.eq('5', 'Expected qty9h value to be equals to 5');
    expect(await proNSCLUpdatePage.getQty10hInput()).to.eq('5', 'Expected qty10h value to be equals to 5');
    expect(await proNSCLUpdatePage.getQty11hInput()).to.eq('5', 'Expected qty11h value to be equals to 5');
    expect(await proNSCLUpdatePage.getQty12hInput()).to.eq('5', 'Expected qty12h value to be equals to 5');
    expect(await proNSCLUpdatePage.getQty13hInput()).to.eq('5', 'Expected qty13h value to be equals to 5');
    expect(await proNSCLUpdatePage.getQty14hInput()).to.eq('5', 'Expected qty14h value to be equals to 5');
    expect(await proNSCLUpdatePage.getReject1hInput()).to.eq('5', 'Expected reject1h value to be equals to 5');
    expect(await proNSCLUpdatePage.getReject2hInput()).to.eq('5', 'Expected reject2h value to be equals to 5');
    expect(await proNSCLUpdatePage.getReject3hInput()).to.eq('5', 'Expected reject3h value to be equals to 5');
    expect(await proNSCLUpdatePage.getReject4hInput()).to.eq('5', 'Expected reject4h value to be equals to 5');
    expect(await proNSCLUpdatePage.getReject5hInput()).to.eq('5', 'Expected reject5h value to be equals to 5');
    expect(await proNSCLUpdatePage.getReject6hInput()).to.eq('5', 'Expected reject6h value to be equals to 5');
    expect(await proNSCLUpdatePage.getReject7hInput()).to.eq('5', 'Expected reject7h value to be equals to 5');
    expect(await proNSCLUpdatePage.getReject8hInput()).to.eq('5', 'Expected reject8h value to be equals to 5');
    expect(await proNSCLUpdatePage.getReject9hInput()).to.eq('5', 'Expected reject9h value to be equals to 5');
    expect(await proNSCLUpdatePage.getReject10hInput()).to.eq('5', 'Expected reject10h value to be equals to 5');
    expect(await proNSCLUpdatePage.getReject11hInput()).to.eq('5', 'Expected reject11h value to be equals to 5');
    expect(await proNSCLUpdatePage.getReject12hInput()).to.eq('5', 'Expected reject12h value to be equals to 5');
    expect(await proNSCLUpdatePage.getReject13hInput()).to.eq('5', 'Expected reject13h value to be equals to 5');
    expect(await proNSCLUpdatePage.getReject14hInput()).to.eq('5', 'Expected reject14h value to be equals to 5');
    expect(await proNSCLUpdatePage.getMan1hInput()).to.eq('5', 'Expected man1h value to be equals to 5');
    expect(await proNSCLUpdatePage.getMan2hInput()).to.eq('5', 'Expected man2h value to be equals to 5');
    expect(await proNSCLUpdatePage.getMan3hInput()).to.eq('5', 'Expected man3h value to be equals to 5');
    expect(await proNSCLUpdatePage.getMan4hInput()).to.eq('5', 'Expected man4h value to be equals to 5');
    expect(await proNSCLUpdatePage.getMan5hInput()).to.eq('5', 'Expected man5h value to be equals to 5');
    expect(await proNSCLUpdatePage.getMan6hInput()).to.eq('5', 'Expected man6h value to be equals to 5');
    expect(await proNSCLUpdatePage.getMan7hInput()).to.eq('5', 'Expected man7h value to be equals to 5');
    expect(await proNSCLUpdatePage.getMan8hInput()).to.eq('5', 'Expected man8h value to be equals to 5');
    expect(await proNSCLUpdatePage.getMan9hInput()).to.eq('5', 'Expected man9h value to be equals to 5');
    expect(await proNSCLUpdatePage.getMan10hInput()).to.eq('5', 'Expected man10h value to be equals to 5');
    expect(await proNSCLUpdatePage.getMan11hInput()).to.eq('5', 'Expected man11h value to be equals to 5');
    expect(await proNSCLUpdatePage.getMan12hInput()).to.eq('5', 'Expected man12h value to be equals to 5');
    expect(await proNSCLUpdatePage.getMan13hInput()).to.eq('5', 'Expected man13h value to be equals to 5');
    expect(await proNSCLUpdatePage.getMan14hInput()).to.eq('5', 'Expected man14h value to be equals to 5');
    expect(await proNSCLUpdatePage.getManOnly7HoursInput()).to.eq('5', 'Expected manOnly7Hours value to be equals to 5');
    expect(await proNSCLUpdatePage.getFix1hInput()).to.eq('5', 'Expected fix1h value to be equals to 5');
    expect(await proNSCLUpdatePage.getFix2hInput()).to.eq('5', 'Expected fix2h value to be equals to 5');
    expect(await proNSCLUpdatePage.getFix3hInput()).to.eq('5', 'Expected fix3h value to be equals to 5');
    expect(await proNSCLUpdatePage.getFix4hInput()).to.eq('5', 'Expected fix4h value to be equals to 5');
    expect(await proNSCLUpdatePage.getFix5hInput()).to.eq('5', 'Expected fix5h value to be equals to 5');
    expect(await proNSCLUpdatePage.getFix6hInput()).to.eq('5', 'Expected fix6h value to be equals to 5');
    expect(await proNSCLUpdatePage.getFix7hInput()).to.eq('5', 'Expected fix7h value to be equals to 5');
    expect(await proNSCLUpdatePage.getFix8hInput()).to.eq('5', 'Expected fix8h value to be equals to 5');
    expect(await proNSCLUpdatePage.getFix9hInput()).to.eq('5', 'Expected fix9h value to be equals to 5');
    expect(await proNSCLUpdatePage.getFix10hInput()).to.eq('5', 'Expected fix10h value to be equals to 5');
    expect(await proNSCLUpdatePage.getFix11hInput()).to.eq('5', 'Expected fix11h value to be equals to 5');
    expect(await proNSCLUpdatePage.getFix12hInput()).to.eq('5', 'Expected fix12h value to be equals to 5');
    expect(await proNSCLUpdatePage.getFix13hInput()).to.eq('5', 'Expected fix13h value to be equals to 5');
    expect(await proNSCLUpdatePage.getFix14hInput()).to.eq('5', 'Expected fix14h value to be equals to 5');

    await proNSCLUpdatePage.save();
    expect(await proNSCLUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await proNSCLComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last ProNSCL', async () => {
    const nbButtonsBeforeDelete = await proNSCLComponentsPage.countDeleteButtons();
    await proNSCLComponentsPage.clickOnLastDeleteButton();

    proNSCLDeleteDialog = new ProNSCLDeleteDialog();
    expect(await proNSCLDeleteDialog.getDialogTitle()).to.eq('ctApp.proNSCL.delete.question');
    await proNSCLDeleteDialog.clickOnConfirmButton();

    expect(await proNSCLComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
