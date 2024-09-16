using Microsoft.Extensions.DependencyInjection;
using olimpiclink.database.Data;
using olimpiclink.database.Models;

var builder = WebApplication.CreateBuilder(args);
builder.WebHost.UseUrls("http://0.0.0.0:5000");

// Add services to the container.
builder.Services.AddControllers();
builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen();
builder.Services.AddScoped<ConnectionContext>();

var app = builder.Build();

// Middleware para logar requisições HTTP
app.Use(async (context, next) =>
{
    // Logar a requisição
    Console.WriteLine($"Método HTTP: {context.Request.Method}");
    Console.WriteLine($"Caminho: {context.Request.Path}");
    Console.WriteLine($"Query String: {context.Request.QueryString}");

    // Chama o próximo middleware no pipeline
    await next.Invoke();

    // Logar a resposta
    Console.WriteLine($"Status da Resposta: {context.Response.StatusCode}");
});

// Configure the HTTP request pipeline.
if (app.Environment.IsDevelopment())
{
    app.UseSwagger();
    app.UseSwaggerUI();
}

app.UseHttpsRedirection();
app.UseAuthorization();
app.MapControllers();

app.Run();
