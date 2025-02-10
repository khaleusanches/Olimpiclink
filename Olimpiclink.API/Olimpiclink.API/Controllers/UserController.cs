using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Olimpiclink.API.Dto;
using Olimpiclink.API.Models;
using Olimpiclink.API.Repository.User;

namespace Olimpiclink.API.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class UserController : ControllerBase
    {
        private readonly IUserRepository _repository;
        public UserController(IUserRepository repository)
        {
            _repository = repository;
        }
        [HttpPost]
        public async Task<ActionResult<ResponseModel<UserModel>>> CreateUser(UserCreateDto newUser)
        {
            var create = await _repository.CreateUser(newUser);
            return Ok(create);
        }
        [HttpGet]
        [Authorize]
        public async Task<ActionResult<ResponseModel<List<UserModel>>>> ListUsers()
        {
            var list = await _repository.ListUsers();
            return Ok(list);
        }
        [HttpGet("Authentication")]
        public async Task<ActionResult<ResponseModel<UserModel>>> LoginUser(string username, string password)
        {
            var loguei = await _repository.LoginUser(username, password);
            return Ok(loguei);
        }
    }
}
