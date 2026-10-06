import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { GYCLQuyCachNPLComponentsPage, GYCLQuyCachNPLDeleteDialog, GYCLQuyCachNPLUpdatePage } from './gycl-quy-cach-npl.page-object';

const expect = chai.expect;

describe('GYCLQuyCachNPL e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let gYCLQuyCachNPLComponentsPage: GYCLQuyCachNPLComponentsPage;
  let gYCLQuyCachNPLUpdatePage: GYCLQuyCachNPLUpdatePage;
  let gYCLQuyCachNPLDeleteDialog: GYCLQuyCachNPLDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load GYCLQuyCachNPLS', async () => {
    await navBarPage.goToEntity('gycl-quy-cach-npl');
    gYCLQuyCachNPLComponentsPage = new GYCLQuyCachNPLComponentsPage();
    await browser.wait(ec.visibilityOf(gYCLQuyCachNPLComponentsPage.title), 5000);
    expect(await gYCLQuyCachNPLComponentsPage.getTitle()).to.eq('ctApp.gYCLQuyCachNPL.home.title');
  });

  it('should load create GYCLQuyCachNPL page', async () => {
    await gYCLQuyCachNPLComponentsPage.clickOnCreateButton();
    gYCLQuyCachNPLUpdatePage = new GYCLQuyCachNPLUpdatePage();
    expect(await gYCLQuyCachNPLUpdatePage.getPageTitle()).to.eq('ctApp.gYCLQuyCachNPL.home.createOrEditLabel');
    await gYCLQuyCachNPLUpdatePage.cancel();
  });

  it('should create and save GYCLQuyCachNPLS', async () => {
    const nbButtonsBeforeCreate = await gYCLQuyCachNPLComponentsPage.countDeleteButtons();

    await gYCLQuyCachNPLComponentsPage.clickOnCreateButton();
    await promise.all([
      gYCLQuyCachNPLUpdatePage.setTenNPLInput('tenNPL'),
      gYCLQuyCachNPLUpdatePage.setNguoiTaoInput('nguoiTao'),
      gYCLQuyCachNPLUpdatePage.setNgayTaoInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      gYCLQuyCachNPLUpdatePage.setUserIdInput('5'),
      gYCLQuyCachNPLUpdatePage.setNAInput('nA'),
      gYCLQuyCachNPLUpdatePage.proGopYChatLuongSelectLastOption()
    ]);
    expect(await gYCLQuyCachNPLUpdatePage.getTenNPLInput()).to.eq('tenNPL', 'Expected TenNPL value to be equals to tenNPL');
    expect(await gYCLQuyCachNPLUpdatePage.getNguoiTaoInput()).to.eq('nguoiTao', 'Expected NguoiTao value to be equals to nguoiTao');
    expect(await gYCLQuyCachNPLUpdatePage.getNgayTaoInput()).to.contain(
      '2001-01-01T02:30',
      'Expected ngayTao value to be equals to 2000-12-31'
    );
    expect(await gYCLQuyCachNPLUpdatePage.getUserIdInput()).to.eq('5', 'Expected userId value to be equals to 5');
    const selectedYesNo = gYCLQuyCachNPLUpdatePage.getYesNoInput();
    if (await selectedYesNo.isSelected()) {
      await gYCLQuyCachNPLUpdatePage.getYesNoInput().click();
      expect(await gYCLQuyCachNPLUpdatePage.getYesNoInput().isSelected(), 'Expected yesNo not to be selected').to.be.false;
    } else {
      await gYCLQuyCachNPLUpdatePage.getYesNoInput().click();
      expect(await gYCLQuyCachNPLUpdatePage.getYesNoInput().isSelected(), 'Expected yesNo to be selected').to.be.true;
    }
    expect(await gYCLQuyCachNPLUpdatePage.getNAInput()).to.eq('nA', 'Expected NA value to be equals to nA');
    await gYCLQuyCachNPLUpdatePage.save();
    expect(await gYCLQuyCachNPLUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await gYCLQuyCachNPLComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last GYCLQuyCachNPL', async () => {
    const nbButtonsBeforeDelete = await gYCLQuyCachNPLComponentsPage.countDeleteButtons();
    await gYCLQuyCachNPLComponentsPage.clickOnLastDeleteButton();

    gYCLQuyCachNPLDeleteDialog = new GYCLQuyCachNPLDeleteDialog();
    expect(await gYCLQuyCachNPLDeleteDialog.getDialogTitle()).to.eq('ctApp.gYCLQuyCachNPL.delete.question');
    await gYCLQuyCachNPLDeleteDialog.clickOnConfirmButton();

    expect(await gYCLQuyCachNPLComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
