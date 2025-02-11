using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Olimpiclink.API.Dto;
using Olimpiclink.API.Models;
using Olimpiclink.API.Repository.User;
using Olimpiclink.API.Service;

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
        public async Task<ActionResult<ResponseModel<List<UserModel>>>> ListUsers()
        {
            var list = await _repository.ListUsers();
            return Ok(list);
        }
        [HttpPost("Authentication")]
        public async Task<ActionResult<ResponseModel<UserModel>>> LoginUser(UserLoginDto loginInfo)
        {
            var loguei = await _repository.LoginUser(loginInfo);
            return Ok(loguei);
        }
        [HttpPost("ValidarEmail")]
        public async Task<ActionResult<ResponseModel<UserModel>>> ValidarEmail(ValidateEmailDto validateEmail)
        {
            var validei = await _repository.ValidarEmail(validateEmail);
            return Ok(validei);
        }
    }
}
