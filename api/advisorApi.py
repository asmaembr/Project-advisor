from flask import Flask, request, jsonify
from joblib import load 
import pandas as pd
import numpy as np

app = Flask(__name__)


model_profile = load('model/profile/profile_model.pkl')
model_top500 = load('model/top500/top500_model.pkl')
model_profit = load('model/profit/profit_model.pkl')

def ProfileModel(new_request_data):
  columns_to_encode = ['Education', 'Projet_Individuel', 'Genre', 'EnVille', 
                     'Influence', 'TroubleMental', 'TraitsCles']
  for column in columns_to_encode:
    encoder = load(f"model/profile/{column}.pkl")
    new_request_data[column] = encoder.transform(new_request_data[column])
  prediction = model_profile.predict(new_request_data)
  return prediction


def Top500Model(new_request_data):
  columns_to_encode = ['Ville', 'Categorie','Investisseurs_providentiels']

  for column in columns_to_encode:
    encoder = load(f"model/top500/{column}.pkl")
    new_request_data[column] = encoder.transform(new_request_data[column])
  prediction = model_top500.predict(new_request_data)
  return prediction


def ProfitModel(new_request_data):
  encoder = load(f"model/profit/Region.pkl")
  new_request_data['Region'] = encoder.transform(new_request_data['Region'])
  prediction = model_profit.predict(new_request_data)
  return prediction


@app.route('/profile', methods=['GET'])
def get_profile():
  valeurs_trait= load('model/profile/traits_valeurs.pkl')
  valeurs_education = load('model/profile/education_valeurs.pkl')
  return jsonify({
    "valeurs_trait": list(valeurs_trait),
    "valeurs_education": list(valeurs_education)
  })

@app.route('/profile', methods=['POST'])
def predict_profile():
  request_data = request.get_json()
  new_request_data = pd.DataFrame([{
    "Education": request_data['education'],
    "Projet_Individuel": request_data['projetIndividuel'], 
    "Age": request_data['age'], 
    "Genre": request_data['genre'],
    "EnVille": request_data['enVille'], 
    "Influence": request_data['influence'], 
    "DegPerseverance": request_data['degPerseverance'], 
    "DegInitiative": request_data['degInitiative'],
    "DegCompetition": request_data['degCompetition'], 
    "DegAutonomie": request_data['degAutonomie'], 
    "DegBesoinReussite": request_data['degBesoinReussite'],
    "DegConfiance": request_data['degConfiance'], 
    "DegSante": request_data['degSante'], 
    "TroubleMental": request_data['troubleMental'], 
    "TraitsCles": request_data['traitsCles']
}])

  
  prediction = ProfileModel(new_request_data)
  if(prediction == 0):
    prediction = "Vous n'avez pas le profil d'un entrepreneur"
  else:
    prediction = "Vous avez le profil d'un entrepreneur"
  return jsonify(prediction)

@app.route('/top500', methods=['GET'])
def get_top500():
  valeurs_ville = load('model/top500/ville_valeurs.pkl') 
  valeurs_categorie = load('model/top500/categorie_valeurs.pkl')
  return jsonify({
    "valeurs_ville": list(valeurs_ville),
    "valeurs_categorie": list(valeurs_categorie)
  })


@app.route('/top500', methods=['POST'])
def predict_top500():
  request_data = request.get_json()
  new_request_data = pd.DataFrame([{
    'Ville': request_data['ville'],
        'Nombre_relations': request_data['nombre_relations'],
        'Tours_financement': request_data['tours_financement'],
        'Capitale_fonds': request_data['capitale_fonds'],
        'Categorie': request_data['categorie'],
        'Investisseurs_providentiels': request_data['investisseurs_providentiels'],
        'Nombre_participants': request_data['nombre_participants'],

    }])
  prediction = Top500Model(new_request_data)
  if(prediction == 1):
    prediction = "Votre startup peut devenir un des top 500"
  else:
    prediction = "Votre startup nécessite des améliorations pour devenir un des top 500"
  return jsonify(prediction)

@app.route('/profit', methods=['GET'])
def get_profit():
  valeurs_region = load('model/profit/region_valeurs.pkl')
  return jsonify({
    "valeurs_region": list(valeurs_region)
  })

@app.route('/profit', methods=['POST'])
def predict_profit():
  request_data = request.get_json()
  new_request_data = pd.DataFrame([{
    'R&D': request_data['rnd'],
    'Administration': request_data['administration'],
    'Marketing': request_data['marketing'],
    'Region': request_data['region'],
  }])
  prediction = ProfitModel(new_request_data)
  prediction = str(prediction)
  return jsonify(prediction[2:-2])


if __name__ == '__main__':
    app.run(debug=True)
