const request = require('supertest');
const app = require('../src/server');
const event = require('./mocks/event.mock');


describe('Endpoints', () => {
    
    
    it('should get all shortURL', async () => {
        const res = await request(app)
            .get('/')            
        expect(res.statusCode).toEqual(200);
    });

    it('should create a shortURL', async () => {
        const res = await request(app)
            .post('/shortUrls')
            .send({
                fullUrl: event.full
            });
        expect(res.statusCode).toEqual(200)
        expect(res.body.full).toEqual(event.full)
        expect(res.body.clicks).toEqual(0)
    });

    it('should find one by shortUrl', async () => {

        //create shorturl
        const res = await request(app)
            .post('/shortUrls')
            .send({
                fullUrl: event.full
            });
        //find full url with short url    
        const response = await request(app).get('/'+res.body.short)

        expect(response.statusCode).toEqual(200)
        expect(response.body.full).toEqual(event.full)
        expect(response.body.clicks).toEqual(1)    
    });

    it('should delete one by shortUrl', async () => {

        //create shorturl
        const res = await request(app)
            .post('/shortUrls')
            .send({
                fullUrl: event.full
            });

        //find full url with short url    
        const response = await request(app).get('/delete/'+res.body.short)

        expect(response.statusCode).toEqual(200)
        expect(response.body.ok).toEqual(1)
        expect(response.body.deletedCount).toEqual(1)
    });

});