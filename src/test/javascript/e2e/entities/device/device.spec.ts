import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { DeviceComponentsPage, DeviceDeleteDialog, DeviceUpdatePage } from './device.page-object';

const expect = chai.expect;

describe('Device e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let deviceComponentsPage: DeviceComponentsPage;
  let deviceUpdatePage: DeviceUpdatePage;
  let deviceDeleteDialog: DeviceDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load Devices', async () => {
    await navBarPage.goToEntity('device');
    deviceComponentsPage = new DeviceComponentsPage();
    await browser.wait(ec.visibilityOf(deviceComponentsPage.title), 5000);
    expect(await deviceComponentsPage.getTitle()).to.eq('ctApp.device.home.title');
  });

  it('should load create Device page', async () => {
    await deviceComponentsPage.clickOnCreateButton();
    deviceUpdatePage = new DeviceUpdatePage();
    expect(await deviceUpdatePage.getPageTitle()).to.eq('ctApp.device.home.createOrEditLabel');
    await deviceUpdatePage.cancel();
  });

  it('should create and save Devices', async () => {
    const nbButtonsBeforeCreate = await deviceComponentsPage.countDeleteButtons();

    await deviceComponentsPage.clickOnCreateButton();
    await promise.all([
      deviceUpdatePage.setNameInput('name'),
      deviceUpdatePage.setDevIdsInput('devIds'),
      deviceUpdatePage.setStateTopicInput('stateTopic'),
      deviceUpdatePage.setLwtTopicInput('lwtTopic'),
      deviceUpdatePage.setSwInput('sw'),
      deviceUpdatePage.setModuleInput('module'),
      deviceUpdatePage.setRestartReasonInput('restartReason'),
      deviceUpdatePage.setTimeInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      deviceUpdatePage.setUptimeInput('uptime'),
      deviceUpdatePage.setHostNameInput('hostName'),
      deviceUpdatePage.setIpAddressInput('ipAddress'),
      deviceUpdatePage.lwtSelectLastOption()
    ]);
    expect(await deviceUpdatePage.getNameInput()).to.eq('name', 'Expected Name value to be equals to name');
    expect(await deviceUpdatePage.getDevIdsInput()).to.eq('devIds', 'Expected DevIds value to be equals to devIds');
    expect(await deviceUpdatePage.getStateTopicInput()).to.eq('stateTopic', 'Expected StateTopic value to be equals to stateTopic');
    expect(await deviceUpdatePage.getLwtTopicInput()).to.eq('lwtTopic', 'Expected LwtTopic value to be equals to lwtTopic');
    expect(await deviceUpdatePage.getSwInput()).to.eq('sw', 'Expected Sw value to be equals to sw');
    expect(await deviceUpdatePage.getModuleInput()).to.eq('module', 'Expected Module value to be equals to module');
    expect(await deviceUpdatePage.getRestartReasonInput()).to.eq(
      'restartReason',
      'Expected RestartReason value to be equals to restartReason'
    );
    expect(await deviceUpdatePage.getTimeInput()).to.contain('2001-01-01T02:30', 'Expected time value to be equals to 2000-12-31');
    expect(await deviceUpdatePage.getUptimeInput()).to.eq('uptime', 'Expected Uptime value to be equals to uptime');
    expect(await deviceUpdatePage.getHostNameInput()).to.eq('hostName', 'Expected HostName value to be equals to hostName');
    expect(await deviceUpdatePage.getIpAddressInput()).to.eq('ipAddress', 'Expected IpAddress value to be equals to ipAddress');
    await deviceUpdatePage.save();
    expect(await deviceUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await deviceComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last Device', async () => {
    const nbButtonsBeforeDelete = await deviceComponentsPage.countDeleteButtons();
    await deviceComponentsPage.clickOnLastDeleteButton();

    deviceDeleteDialog = new DeviceDeleteDialog();
    expect(await deviceDeleteDialog.getDialogTitle()).to.eq('ctApp.device.delete.question');
    await deviceDeleteDialog.clickOnConfirmButton();

    expect(await deviceComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
