const ormUrl = require('../src/domain/orm/orm-url-short');
const event = require('./mocks/event.mock');


describe('Validate DB ORM', () => {

    it('create one url short', async () => {

        const res = await ormUrl.CreateOne(event.full, event.short);
        
        expect(res.clicks).toEqual(0);
        expect(res.full).toEqual(event.full);
        expect(res.short).toEqual(event.short);
    });

    it('should find one url short by id', async () => {
        const res = await ormUrl.FindOne(event.short);
        
        expect(res.clicks).toEqual(1);
        expect(res.full).toEqual(event.full);
        expect(res.short).toEqual(event.short);
        
    });

    it('should delete one url short by id', async () => {
        const res = await ormUrl.DeleteOne(event.short);        
        expect(res.n).toEqual(1);
        expect(res.ok).toEqual(1);
        expect(res.deletedCount).toEqual(1);
    });
   
});